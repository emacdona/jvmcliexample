
group "default" {
  targets = [
      "graalDev",
      "temurinDev"
    ] 
  }

target "graalBuild" {
  context = "./graal-builder"
  dockerfile = "Dockerfile"
  output = [ "type=docker" ]
  tags = [ "graal-build:latest" ]
}

target "graalDev" {
  context = "./graal-developer"
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
  context = "./temurinBuilder"
  contexts = { base =  "target:temurinBase" }
  output = [ "type=docker" ]
  tags = [ "temurin-build:latest" ]
}

target "temurinDev" {
  context = "./temurinDeveloper"
  contexts = { base =  "target:temurinBuild" }
  output = [ "type=docker" ]
  tags = [ "temurin-dev:latest" ]
}
