### Java Spring template project

This project is based on a GitLab [Project Template](https://docs.gitlab.com/ee/gitlab-basics/create-project.html).

Improvements can be proposed in the [original project](https://gitlab.com/gitlab-org/project-templates/spring).

### CI/CD with Auto DevOps

This template is compatible with [Auto DevOps](https://docs.gitlab.com/ee/topics/autodevops/).

If Auto DevOps is not already enabled for this project, you can [turn it on](https://docs.gitlab.com/ee/topics/autodevops/#enabling-auto-devops) in the project settings.

If not using Auto DevOps, this is a sample .gitlab-ci.yml
```
variables:
  SECURE_LOG_LEVEL: "debug"

stages:
- build

build-image:
  stage: build
  services:
  - docker:dind
  variables:
    IMAGE: $CI_REGISTRY_IMAGE/$CI_COMMIT_REF_SLUG:$CI_COMMIT_SHA
    IMAGE_LATEST: $CI_REGISTRY_IMAGE/$CI_COMMIT_REF_SLUG:latest
  before_script:
    - docker info
    - docker login -u $CI_REGISTRY_USER -p $CI_REGISTRY_PASSWORD $CI_REGISTRY
  script:
    - docker build -t $IMAGE .
  after_script:
    - docker tag $IMAGE $IMAGE_LATEST
    - docker push $IMAGE
    - docker push $IMAGE_LATEST
```
