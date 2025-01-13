### Java Spring template project

This project is based on a GitLab [Project Template](https://docs.gitlab.com/ee/gitlab-basics/create-project.html).

Improvements can be proposed in the [original project](https://gitlab.com/gitlab-org/project-templates/spring).

### CI/CD with Auto DevOps

This template is compatible with [Auto DevOps](https://docs.gitlab.com/ee/topics/autodevops/).

If Auto DevOps is not already enabled for this project, you can [turn it on](https://docs.gitlab.com/ee/topics/autodevops/#enabling-auto-devops) in the project settings.

If not using Auto DevOps, this is a sample .gitlab-ci.yml
```
default: 
  tags: [ "k8s" ] # Execute on group k8s runners
variables:
  SECURE_LOG_LEVEL: "debug"
stages:
- build
- test
- deploy
build-job:
  stage: build
  script:
  - echo "Compiling the code..."
  - echo "Compile complete."
```
