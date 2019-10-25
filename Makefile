IMAGE := evearakaki/hawk-kotlin
IMAGE_VERSION:= $(shell grep HAWK_VERSION Dockerfile | awk '{print $2}' | cut -d '=' -f 2).$(TRAVIS_BUILD_NUMBER)
VCS_REF=`git rev-parse --short HEAD`

GIT_TAG=$(IMAGE):$(VCS_REF)
BUILD_TAG=$(IMAGE):$(VERSION)
LATEST_TAG=$(IMAGE):latest

build:
	docker build \
		--build-arg VCS_REF=$(VCS_REF) \
		--build-arg IMAGE_VERSION=$(IMAGE_VERSION) \
		-t $(GIT_TAG) .

tag:
	docker tag $(GIT_TAG) $(BUILD_TAG)
	docker tag $(GIT_TAG) $(LATEST_TAG)

login:
	@docker login -u "$($DOCKER_USERNAME)" -p "$($DOCKER_PASSWORD)"

push: login
	docker push $(GIT_TAG)
	docker push $(BUILD_TAG)
	docker push $(LATEST_TAG)