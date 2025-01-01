#/bin/bash

docker run -it --rm --add-host=host.docker.internal:host-gateway --name ubu1 local/ubuntu-net /bin/bash
