FROM ubuntu:rolling

RUN <<EOF
apt-get update
apt-get upgrade -y
apt-get install iputils-ping -y
apt-get install iproute2 -y
apt-get install net-tools -y
apt-get install dnsutils -y
apt-get install curl -y
apt-get install traceroute -y
apt-get install mysql-client -y
EOF

CMD ["/usr/sbin/ip", "addr"]
