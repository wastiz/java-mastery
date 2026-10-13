package com.javamastery.spring.rest;

import com.jayway.jsonpath.JsonPath;
import com.javamastery.spring.rest.domain.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookApiTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    BookRepository repository;

    @BeforeEach
    void clean() {
        repository.deleteAll();
    }

    static String json(String isbn, String title, String author, int year, String price, int stock) {
        return """
                {"isbn": "%s", "title": "%s", "author": "%s", "year": %d, "price": %s, "stock": %d}
                """.formatted(isbn, title, author, year, price, stock);
    }

    long create(String isbn, String title, String author, int year, String price, int stock) throws Exception {
        String body = mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                        .content(json(isbn, title, author, year, price, stock)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(body, "$.id")).longValue();
    }

    void expectProblem(ResultActions r, int status, String title) throws Exception {
        r.andExpect(status().is(status))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(status))
                .andExpect(jsonPath("$.title").value(title));
    }

    @Test
    void createAndGet() throws Exception {
        mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                        .content(json("9780134685991", "Effective Java", "Joshua Bloch", 2018, "45.99", 7)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", matchesPattern("/api/books/\\d+")))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.price").value(45.99))
                .andExpect(jsonPath("$.stock").value(7));

        long id = create("9781617294945", "Spring in Action", "Craig Walls", 2018, "39.99", 3);
        mvc.perform(get("/api/books/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("Craig Walls"))
                .andExpect(jsonPath("$.isbn").value("9781617294945"));
    }

    @Test
    void notFound() throws Exception {
        expectProblem(mvc.perform(get("/api/books/999999")), 404, "Book not found");
        expectProblem(mvc.perform(delete("/api/books/999999")), 404, "Book not found");
    }

    @Test
    void validation() throws Exception {
        ResultActions r = mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content(json("123", " ", "", 1200, "-1", -5)));
        expectProblem(r, 400, "Validation failed");
        r.andExpect(jsonPath("$.errors.isbn").value("isbn must be 13 digits"))
                .andExpect(jsonPath("$.errors.title").value("title is required"))
                .andExpect(jsonPath("$.errors.author").value("author is required"))
                .andExpect(jsonPath("$.errors.year").value("year must be between 1450 and 2100"))
                .andExpect(jsonPath("$.errors.price").value("price must be >= 0"))
                .andExpect(jsonPath("$.errors.stock").value("stock must be >= 0"));

        mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"No price\", \"author\": \"X\", \"year\": 2000, \"isbn\": \"1234567890123\", \"stock\": 1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.price").value("price is required"));
    }

    @Test
    void duplicateIsbn() throws Exception {
        create("9780134685991", "Effective Java", "Joshua Bloch", 2018, "45.99", 7);
        expectProblem(mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content(json("9780134685991", "Copy", "Someone", 2020, "1.00", 1))), 409, "Duplicate ISBN");
    }

    @Test
    void update() throws Exception {
        long a = create("1111111111111", "A", "Author A", 2000, "10.00", 1);
        long b = create("2222222222222", "B", "Author B", 2001, "20.00", 2);
        mvc.perform(put("/api/books/" + a).contentType(MediaType.APPLICATION_JSON)
                        .content(json("1111111111111", "A (2nd ed.)", "Author A", 2010, "15.50", 4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("A (2nd ed.)"))
                .andExpect(jsonPath("$.id").value(a));
        expectProblem(mvc.perform(put("/api/books/" + a).contentType(MediaType.APPLICATION_JSON)
                .content(json("2222222222222", "A", "Author A", 2010, "15.50", 4))), 409, "Duplicate ISBN");
        expectProblem(mvc.perform(put("/api/books/424242").contentType(MediaType.APPLICATION_JSON)
                .content(json("3333333333333", "C", "C", 2010, "1.00", 1))), 404, "Book not found");
        mvc.perform(get("/api/books/" + b)).andExpect(jsonPath("$.title").value("B"));
    }

    @Test
    void stock() throws Exception {
        long id = create("1111111111111", "A", "Author A", 2000, "10.00", 3);
        mvc.perform(patch("/api/books/" + id + "/stock").contentType(MediaType.APPLICATION_JSON).content("{\"delta\": -2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock").value(1));
        expectProblem(mvc.perform(patch("/api/books/" + id + "/stock").contentType(MediaType.APPLICATION_JSON).content("{\"delta\": -2}")),
                409, "Out of stock");
        mvc.perform(get("/api/books/" + id)).andExpect(jsonPath("$.stock").value(1));
    }

    @Test
    void deleteBook() throws Exception {
        long id = create("1111111111111", "A", "Author A", 2000, "10.00", 3);
        mvc.perform(delete("/api/books/" + id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/books/" + id)).andExpect(status().isNotFound());
    }

    @Test
    void listFilterSortPage() throws Exception {
        create("1000000000001", "spring Boot Up", "Mark Heckler", 2021, "40.00", 1);
        create("1000000000002", "Java Concurrency in Practice", "Brian Goetz", 2006, "55.00", 1);
        create("1000000000003", "Effective Java", "Joshua Bloch", 2018, "45.99", 1);
        create("1000000000004", "Clean Code", "Robert Martin", 2008, "35.00", 1);
        create("1000000000005", "Java Puzzlers", "Joshua Bloch", 2005, "30.00", 1);

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(5))
                .andExpect(jsonPath("$.content[*].title", contains("Clean Code", "Effective Java",
                        "Java Concurrency in Practice", "Java Puzzlers", "spring Boot Up")));

        mvc.perform(get("/api/books").param("author", "bloch").param("sort", "year"))
                .andExpect(jsonPath("$.content[*].title", contains("Java Puzzlers", "Effective Java")));

        mvc.perform(get("/api/books").param("sort", "price").param("size", "2").param("page", "1"))
                .andExpect(jsonPath("$.content[*].title", contains("spring Boot Up", "Effective Java")))
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.totalPages").value(3));

        mvc.perform(get("/api/books").param("page", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }

    @Test
    void badPaging() throws Exception {
        expectProblem(mvc.perform(get("/api/books").param("size", "0")), 400, "Bad request");
        expectProblem(mvc.perform(get("/api/books").param("size", "101")), 400, "Bad request");
        expectProblem(mvc.perform(get("/api/books").param("sort", "rating")), 400, "Bad request");
    }
}
