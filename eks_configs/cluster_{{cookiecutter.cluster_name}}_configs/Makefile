BUILDER = ./builder/build.py

DOCKER_REPOSITORY ?= docker.io/ifoodhub

export DOCKER_REPOSITORY

.PHONY: help lint base-images child-images all-images list-images new-image

help:  ## Show available commands
	@echo "Available commands:"
	@echo
	@sed -n -E -e 's|^([a-z-]+):.+## (.+)|\1@\2|p' $(MAKEFILE_LIST) | column -s '@' -t

lint:  ## Run lint commands
	pre-commit run --all-files --verbose --color always

base-images:  ## Build the base images
	IMAGES_DIR=images/base $(BUILDER)

child-images:  ## Build the child images
	IMAGES_DIR=images/child $(BUILDER)

all-images: base-images child-images  ## Build ALL the things!

list-images:  ## List all images in the repository
	@echo "Available images:"
	@echo
	@find images/ -name 'Dockerfile*' | sed -E -e 's|^images/||' -e 's|/Dockerfile.*||' | sort

new-image:  ## Add a new image to this repository
	cookiecutter cookiecutter/
