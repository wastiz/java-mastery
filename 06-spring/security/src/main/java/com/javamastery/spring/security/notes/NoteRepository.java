package com.javamastery.spring.security.notes;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/** In-memory хранилище заметок. Готово. */
@Repository
public class NoteRepository {

    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final AtomicLong ids = new AtomicLong();

    public Note create(String owner, String text) {
        Note n = new Note(ids.incrementAndGet(), owner, text, Instant.now());
        notes.put(n.id(), n);
        return n;
    }

    public List<Note> findByOwner(String owner) {
        return notes.values().stream().filter(n -> n.owner().equals(owner)).sorted(Comparator.comparingLong(Note::id)).toList();
    }

    public Optional<Note> findById(long id) {
        return Optional.ofNullable(notes.get(id));
    }

    public void delete(long id) {
        notes.remove(id);
    }

    public int count() {
        return notes.size();
    }

    public void deleteAll() {
        notes.clear();
    }
}
