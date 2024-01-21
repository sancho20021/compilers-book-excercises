plugins {
    id("java")
    antlr
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    antlr("org.antlr:antlr4:4.13.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    generateGrammarSource {
        arguments.run {
            add("-package")
            add("chap2lexing.minijava.generated")
        }
        outputDirectory = file("src/main/java/chap2lexing/minijava/generated")
    }
}
