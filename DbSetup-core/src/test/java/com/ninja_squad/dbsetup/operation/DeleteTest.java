/*
 * The MIT License
 *
 * Copyright (c) 2012, Ninja Squad
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ninja_squad.dbsetup.operation;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import com.ninja_squad.dbsetup.bind.Binder;

/**
 * @author R. Flores
 */
public class DeleteTest {
    
//    Binder bBinder = mock(Binder.class);
//    Binder dBinder = mock(Binder.class);
    
    Insert insert;
    
    @Before
    public void setup(){
//    insert = Insert.into("A")
//                          .columns("a", "b")
//                          .values("a1", "b1")
//                          .values("a2", "b2")
//                          .row().column("b", "b3")
//                                .column("a", "a3")
//                                .end()
//                          .row().column("a", "a4")
//                                .end()
//                          .withDefaultValue("c", "c3")
//                          .withDefaultValue("d", "d4")
//                          .withBinder(bBinder, "b")
//                          .withBinder(dBinder, "d")
//                          .build();
    insert = Insert.into("ANCESTRAL_CHROMOSOME_T")
            .columns("ancestral_chromosome_id", "ancestral_chromosome_name")
            .values(1001L, "anc_chr_1")
            .values(1002L, "anc_chr_2")
            .row().column("ancestral_chromosome_id", 1003L)
                  .column("ancestral_chromosome_name", "anc_chr_3")
                  .end()
            .withDefaultValue("group_id", "23")
//            .withBinder(dBinder, "d")
            .build();
    }
    
    @Test
    public void fromWorks() throws IOException {
        Delete d = Delete.from(insert, "ancestral_chromosome_id");
        String q = d.getGenetaredSqlQuery();
        System.out.println(q);
        Path p =Files.createTempFile("test", "");
        File f = p.toFile();
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        fw.write(q);
        fw.close();
    }
    
    @Test(expected=NullPointerException.class)
    public void fromNull() {
        Delete.from(null,null);
    }
//    
//    @Test
//    public void fromWorks() throws SQLException {
//        
//        
//        testFrom(DeleteAll.from("A", "B"));
//        testFrom(DeleteAll.from(Arrays.asList("A", "B")));
//        testFrom(Operations.deleteAllFrom("A", "B"));
//        testFrom(Operations.deleteAllFrom(Arrays.asList("A", "B")));
//    }
//
//    @Test
//    public void toStringWorks() {
//        assertEquals("delete from A", DeleteAll.from("A").toString());
//    }
//
//    @Test
//    public void equalsAndHashCodeWork() {
//        assertEquals(DeleteAll.from("A"), Operations.deleteAllFrom("A"));
//        assertEquals(DeleteAll.from("A").hashCode(), DeleteAll.from("A").hashCode());
//        assertFalse(DeleteAll.from("A").equals(DeleteAll.from("B")));
//        assertFalse(DeleteAll.from("A").equals(null));
//        assertFalse(DeleteAll.from("A").equals("hello"));
//        DeleteAll a = DeleteAll.from("A");
//        assertEquals(a, a);
//    }
//
//    private void testFrom(Operation deleteAllFromAandB) throws SQLException {
//        Connection connection = mock(Connection.class);
//        Statement stmt = mock(Statement.class);
//        when(connection.createStatement()).thenReturn(stmt);
//        deleteAllFromAandB.execute(connection, DefaultBinderConfiguration.INSTANCE);
//        InOrder inOrder = inOrder(stmt);
//        inOrder.verify(stmt).executeUpdate("delete from A");
//        inOrder.verify(stmt).close();
//        inOrder.verify(stmt).executeUpdate("delete from B");
//        inOrder.verify(stmt).close();
//    }
}
