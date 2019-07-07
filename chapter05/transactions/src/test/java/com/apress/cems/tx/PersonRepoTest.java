/*
Freeware License, some rights reserved

Copyright (c) 2019 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.cems.tx;

import com.apress.cems.dao.Person;
import com.apress.cems.repos.PersonRepo;
import com.apress.cems.tx.config.AppConfig;
import com.apress.cems.tx.config.TestTransactionalDbConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
@Rollback(false)
@Disabled // De-Comment this annotation and run to see the tests fail
class PersonRepoTest {

    @Autowired
    PersonRepo repo;

    //@Commit
    @Test
    @Transactional
    void testCreatePerson(){
        Person person = new Person();
        person.setId(99L);
        person.setUsername("test.user");
        person.setFirstName("test");
        person.setLastName("user");
        person.setPassword("password");
        person.setHiringDate(LocalDate.now());
        person.setCreatedAt(LocalDate.now());
        person.setModifiedAt(LocalDate.now());
        repo.save(person);
    }

    // this will fail
    @Test
    void testCountPersons(){
        long result = repo.count();
        assertEquals(2, result);
    }

}