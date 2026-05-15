package service;

import beans.Project;
import dao.ProjectDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProjectService {
    @Inject
    private ProjectDao projectDao;

    @Transactional
    public Project create(Project project) {
        if(project.isValid()) {
            //after the creation the method return the same project with the id
            return projectDao.create(project);
        }
        return null;
    }
}
