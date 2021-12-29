package com.jrp.pma.dao;


import com.jrp.pma.entities.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/*@ContextConfiguration(classes = ProjectManagmentApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest*/
@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD ,
    scripts = {"classpath:schema.sql","classpath:data.sql"}),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD ,
        scripts = {"classpath:drop.sql"})
})
public class ProjectRepositoryIntegrationTest {
    @Autowired
    ProjectRepository projectRepo;

    @Test
    public void  ifNewProjectSaved_thenSuccess(){
        Project newProject =
            new Project("New Test Project" , "COMPLETE" , "Test Description");
        projectRepo.save(newProject);
        assertEquals(5,projectRepo.findAll().size());
    }

}
