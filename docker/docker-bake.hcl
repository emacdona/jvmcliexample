
group "default" {
  targets = [
      "graalDev",
      "temurinDev"
    ] 
  }

target "graalBase" {
  context = "./graalBase"
  dockerfile = "./graalBase/Dockerfile"
  output = [ "type=docker" ]
  tags = [ "graal-base:latest" ]
}

target "graalBuild" {
  context = "./graalBuild"
  dockerfile = "./graalBuild/Dockerfile"
  contexts = { graalBase = "target:graalBase" }
  output = [ "type=docker" ]
  tags = [ "graal-build:latest" ]
}

target "graalDev" {
  context = "./graalDev"
  contexts = { base =  "target:graalBuild" }
  output = [ "type=docker" ]
  tags = [ "graal-dev:latest" ]
}

target "temurinBase" {
  context = "./temurinBase"
  output = [ "type=docker" ]
  tags = [ "temurin-base:latest" ]
}

target "temurinBuild" {
  context = "./temurinBuild"
  contexts = { base =  "target:temurinBase" }
  output = [ "type=docker" ]
  tags = [ "temurin-build:latest" ]
}

target "temurinDev" {
  context = "./temurinDev"
  contexts = { base =  "target:temurinBuild" }
  output = [ "type=docker" ]
  tags = [ "temurin-dev:latest" ]
}
