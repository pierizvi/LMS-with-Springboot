package com.example.springbootcrudapp.config;

import com.example.springbootcrudapp.entity.Author;
import com.example.springbootcrudapp.entity.Book;
import com.example.springbootcrudapp.repository.AuthorRepository;
import com.example.springbootcrudapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public DataLoader(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        // Check if the database is already populated
        if (authorRepository.count() > 0 || bookRepository.count() > 0) {
            return;
        }

        // Create and save authors
        List<Author> authors = createAuthors();
        List<Author> savedAuthors = authorRepository.saveAll(authors);

        // Create and save books
        List<Book> books = createBooks(savedAuthors);
        bookRepository.saveAll(books);

        System.out.println("Sample data loaded successfully.");
    }

    private List<Author> createAuthors() {
        List<Author> authors = new ArrayList<>();

        // Author 1
        Author author1 = new Author();
        author1.setFirstName("J.K.");
        author1.setLastName("Rowling");
        author1.setEmail("jk.rowling@example.com");
        author1.setBiography("Joanne Rowling CH, OBE, HonFRSE, FRCPE, FRSL, better known by her pen name J. K. Rowling, is a British author and philanthropist. She is best known for writing the Harry Potter fantasy series.");
        author1.setBirthYear(1965);
        authors.add(author1);

        // Author 2
        Author author2 = new Author();
        author2.setFirstName("George");
        author2.setLastName("Orwell");
        author2.setEmail("george.orwell@example.com");
        author2.setBiography("Eric Arthur Blair, better known by his pen name George Orwell, was an English novelist, essayist, journalist, and critic. His work is characterized by lucid prose, biting social criticism, opposition to totalitarianism, and outspoken support of democratic socialism.");
        author2.setBirthYear(1903);
        authors.add(author2);

        // Author 3
        Author author3 = new Author();
        author3.setFirstName("Jane");
        author3.setLastName("Austen");
        author3.setEmail("jane.austen@example.com");
        author3.setBiography("Jane Austen was an English novelist known primarily for her six major novels, which interpret, critique and comment upon the British landed gentry at the end of the 18th century.");
        author3.setBirthYear(1775);
        authors.add(author3);

        // Author 4
        Author author4 = new Author();
        author4.setFirstName("Ernest");
        author4.setLastName("Hemingway");
        author4.setEmail("ernest.hemingway@example.com");
        author4.setBiography("Ernest Miller Hemingway was an American novelist, short-story writer, and journalist. His economical and understated style—which he termed the iceberg theory—had a strong influence on 20th-century fiction.");
        author4.setBirthYear(1899);
        authors.add(author4);

        // Author 5
        Author author5 = new Author();
        author5.setFirstName("Agatha");
        author5.setLastName("Christie");
        author5.setEmail("agatha.christie@example.com");
        author5.setBiography("Dame Agatha Mary Clarissa Christie, Lady Mallowan, DBE was an English writer known for her 66 detective novels and 14 short story collections, particularly those revolving around fictional detectives Hercule Poirot and Miss Marple.");
        author5.setBirthYear(1890);
        authors.add(author5);

        // Author 6
        Author author6 = new Author();
        author6.setFirstName("Stephen");
        author6.setLastName("King");
        author6.setEmail("stephen.king@example.com");
        author6.setBiography("Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels. His books have sold more than 350 million copies.");
        author6.setBirthYear(1947);
        authors.add(author6);

        // Author 7
        Author author7 = new Author();
        author7.setFirstName("Harper");
        author7.setLastName("Lee");
        author7.setEmail("harper.lee@example.com");
        author7.setBiography("Nelle Harper Lee was an American novelist best known for her 1960 novel To Kill a Mockingbird. It won the 1961 Pulitzer Prize and has become a classic of modern American literature.");
        author7.setBirthYear(1926);
        authors.add(author7);

        // Author 8
        Author author8 = new Author();
        author8.setFirstName("Mark");
        author8.setLastName("Twain");
        author8.setEmail("mark.twain@example.com");
        author8.setBiography("Samuel Langhorne Clemens, known by his pen name Mark Twain, was an American writer, humorist, entrepreneur, publisher, and lecturer. He was lauded as the 'greatest humorist the United States has produced'.");
        author8.setBirthYear(1835);
        authors.add(author8);

        // Author 9
        Author author9 = new Author();
        author9.setFirstName("Leo");
        author9.setLastName("Tolstoy");
        author9.setEmail("leo.tolstoy@example.com");
        author9.setBiography("Count Lev Nikolayevich Tolstoy, usually referred to in English as Leo Tolstoy, was a Russian writer who is regarded as one of the greatest authors of all time.");
        author9.setBirthYear(1828);
        authors.add(author9);

        // Author 10
        Author author10 = new Author();
        author10.setFirstName("F. Scott");
        author10.setLastName("Fitzgerald");
        author10.setEmail("scott.fitzgerald@example.com");
        author10.setBiography("Francis Scott Key Fitzgerald was an American fiction writer, whose works helped to illustrate the flamboyance and excess of the Jazz Age.");
        author10.setBirthYear(1896);
        authors.add(author10);

        return authors;
    }

    private List<Book> createBooks(List<Author> authors) {
        List<Book> books = new ArrayList<>();

        // Books by J.K. Rowling
        Book book1 = new Book();
        book1.setTitle("Harry Potter and the Philosopher's Stone");
        book1.setIsbn("9780747532743");
        book1.setGenre("Fantasy");
        book1.setPublicationDate(LocalDate.of(1997, 6, 26));
        book1.setPrice(new BigDecimal("19.99"));
        book1.setDescription("Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle.");
        book1.setAuthor(authors.get(0));
        books.add(book1);

        // Books by George Orwell
        Book book2 = new Book();
        book2.setTitle("1984");
        book2.setIsbn("9780451524935");
        book2.setGenre("Dystopian");
        book2.setPublicationDate(LocalDate.of(1949, 6, 8));
        book2.setPrice(new BigDecimal("12.99"));
        book2.setDescription("Among the seminal texts of the 20th century, Nineteen Eighty-Four is a rare work that grows more haunting as its futuristic purgatory becomes more real.");
        book2.setAuthor(authors.get(1));
        books.add(book2);

        Book book3 = new Book();
        book3.setTitle("Animal Farm");
        book3.setIsbn("9780451526342");
        book3.setGenre("Political Satire");
        book3.setPublicationDate(LocalDate.of(1945, 8, 17));
        book3.setPrice(new BigDecimal("9.99"));
        book3.setDescription("A farm is taken over by its overworked, mistreated animals. With flaming idealism and stirring slogans, they set out to create a paradise of progress, justice, and equality.");
        book3.setAuthor(authors.get(1));
        books.add(book3);

        // Books by Jane Austen
        Book book4 = new Book();
        book4.setTitle("Pride and Prejudice");
        book4.setIsbn("9780141439518");
        book4.setGenre("Romance");
        book4.setPublicationDate(LocalDate.of(1813, 1, 28));
        book4.setPrice(new BigDecimal("8.99"));
        book4.setDescription("Since its immediate success in 1813, Pride and Prejudice has remained one of the most popular novels in the English language.");
        book4.setAuthor(authors.get(2));
        books.add(book4);

        // Books by Ernest Hemingway
        Book book5 = new Book();
        book5.setTitle("The Old Man and the Sea");
        book5.setIsbn("9780684801223");
        book5.setGenre("Literary Fiction");
        book5.setPublicationDate(LocalDate.of(1952, 9, 1));
        book5.setPrice(new BigDecimal("10.99"));
        book5.setDescription("The Old Man and the Sea is one of Hemingway's most enduring works. Told in language of great simplicity and power, it is the story of an old Cuban fisherman, down on his luck, and his supreme ordeal.");
        book5.setAuthor(authors.get(3));
        books.add(book5);

        // Books by Agatha Christie
        Book book6 = new Book();
        book6.setTitle("Murder on the Orient Express");
        book6.setIsbn("9780062693662");
        book6.setGenre("Mystery");
        book6.setPublicationDate(LocalDate.of(1934, 1, 1));
        book6.setPrice(new BigDecimal("11.99"));
        book6.setDescription("Just after midnight, the famous Orient Express is stopped in its tracks by a snowdrift. By morning, the millionaire Samuel Edward Ratchett lies dead in his compartment, stabbed a dozen times, his door locked from the inside.");
        book6.setAuthor(authors.get(4));
        books.add(book6);

        // Books by Stephen King
        Book book7 = new Book();
        book7.setTitle("The Shining");
        book7.setIsbn("9780307743657");
        book7.setGenre("Horror");
        book7.setPublicationDate(LocalDate.of(1977, 1, 28));
        book7.setPrice(new BigDecimal("14.99"));
        book7.setDescription("Jack Torrance's new job at the Overlook Hotel is the perfect chance for a fresh start. As the off-season caretaker at the atmospheric old hotel, he'll have plenty of time to spend reconnecting with his family and working on his writing.");
        book7.setAuthor(authors.get(5));
        books.add(book7);

        // Books by Harper Lee
        Book book8 = new Book();
        book8.setTitle("To Kill a Mockingbird");
        book8.setIsbn("9780061120084");
        book8.setGenre("Fiction");
        book8.setPublicationDate(LocalDate.of(1960, 7, 11));
        book8.setPrice(new BigDecimal("12.99"));
        book8.setDescription("The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it, To Kill A Mockingbird became both an instant bestseller and a critical success when it was first published in 1960.");
        book8.setAuthor(authors.get(6));
        books.add(book8);

        // Books by Mark Twain
        Book book9 = new Book();
        book9.setTitle("The Adventures of Huckleberry Finn");
        book9.setIsbn("9780143107323");
        book9.setGenre("Adventure");
        book9.setPublicationDate(LocalDate.of(1884, 12, 10));
        book9.setPrice(new BigDecimal("9.99"));
        book9.setDescription("A nineteenth-century boy from a Mississippi River town recounts his adventures as he travels down the river with a runaway slave, encountering a family involved in a feud, two scoundrels pretending to be royalty, and Tom Sawyer's aunt who mistakes him for Tom.");
        book9.setAuthor(authors.get(7));
        books.add(book9);

        // Books by Leo Tolstoy
        Book book10 = new Book();
        book10.setTitle("War and Peace");
        book10.setIsbn("9781400079988");
        book10.setGenre("Historical Fiction");
        book10.setPublicationDate(LocalDate.of(1869, 1, 1));
        book10.setPrice(new BigDecimal("19.99"));
        book10.setDescription("War and Peace broadly focuses on Napoleon's invasion of Russia in 1812 and follows three of the most well-known characters in literature: Pierre Bezukhov, the illegitimate son of a count who is fighting for his inheritance and yearning for spiritual fulfillment.");
        book10.setAuthor(authors.get(8));
        books.add(book10);

        return books;
    }
}