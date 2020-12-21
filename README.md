# AwsChangeManager

AWSChangeManager is a barely-opinionated framework for automatic deployment and monitoring of software, written in Java with some out of the box wrappers to
manage AWS resources. The abstractions provided are more of a suggestion or a way to get started. The value is in the deployment strategy.

## How it works

Here's a sample application using AwsChangeManager:

```java
// import ...

public class Main {

    public static void main(String[] args) {
        AwsClientContext awsClientContext = new AwsClientContext();
        awsClientContext.add(SsmClient.builder().build());

        VersionStore versionStore = new AwsSsmVersionStore(awsClientContext.getSsmClient(), "ChangeSetTestKey");
        App app = new App(versionStore);

        app.addVersionChangeSet(new Version("0.0.1"), new ChangeSet00000001());
        app.addVersionChangeSet(new Version("0.0.2"), new ChangeSet00000002());

        app.update();
    }


}
```

In the example, we:
1. Create an AwsClientContext to store all of those SDK clients
1. Create a VersionStore backed by AWS Systems Manager Parameter Store
1. Create ChangeSets with code that updates your application
1. Register the ChangeSets with your application using a Version
1. Update the app

On execution:
1. The app inspects the tracked resources in each ChangeSet, compiling a Context
1. The Context at the currently deployed version is inspected to see if the application is still in a working state
1. The application deploys the next ChangeSet and once again, inspects the application to verify its state
1. This is repeated until there are no more ChangeSets or a ChangeSet fails to deploy succesfully

What to do from here:
* Create a new ChangeSet with steps to update your application
* Add it to the application when it's ready to be deployed
* Extend the library however you see fit


### ChangeSets
A ChangeSet must implement a "deploy" and "isDeployed" method. This a short script from how to get from the currently deployed version of your application to the next.

## Motivation
Arugably, this project is not just for AWS projects. AwsChangeManager was created to fill a gap in the current AWS tech stack: change management. 
AWS applications are often complex and heterogenous. 
They have many sub-projects and dependencies. It's hard to know what to build, when to build it, and how to deploy it. As such, it's very hard to
automate. The goal of automation is to assist and to not get in the way. Deployment has been simplified in the past by using opinionated frameworks.
However, live-updates have been a part of software engineering for decades. It's even tought in college classes. Further more, automation of stateful
changes or changes to a persistence layer have also been abstracted by tools like ORMs. Another solution is to keep a list of database change scripts.
While it's not possible to replicate the behavior of the external world that creates the data that we manage, it's well within our ability to manage
the changes that we apply to our own systems. Thinking of the AWS Control Plane as an extension of our application state dramatically simplifies the
process. In the past, if we needed a queue, we initialized one. If our queue lived in a web application server, we requested one. Service discovery was
a solved problem. Today, the complexity of the cloud and DevOps practices have pushed so much onto developers that it's hard to see basic engineering
problems when they resurface. AwsChangeManager puts all these ideas into practice.

### Drawbacks
This is not a mature project so there is a high startup cost. CloudFormation typically hides the API calls needed to manage resources and those need to be
reimplemented in AwsChangeManager. There is a lot of room to improve by supporting other languages and providing more out of the box resources for different
cloud providers or other technologies. There's also room to grow in terms of supporting deployment strategies like Green/Blue deployments or other
common patterns in DevOps. More supporting for managing building sub-projects would also be helpful in managing heterogenous projects. 

### What's wrong with cloudformation or CDK?

What isn't wrong with CloudFormation and CDK? For starters, they are both fundamentally declarative. While CDK is a way to programmatically generate declarative 
configurations. When we hand these over to CloudFormation, it has to make a best guess about what to do with the resources. While it's great to have the
dependency resolution and the per-resource upgrade rules handled for us, it leaves us really unprepared for when it cannot reason about your intentions.
This happens all the time when you make changes that impact your data plane. If your application is writing to one format and you need to switch it over,
you most likely have a lot more to think about than how to update your Lambda script. Coordinating complex deployments is next to impossible. 

Once we think we have a grasp on the tools AWS provides, we soon build our own pipelines to make them go faster. What happens when our architecture changes?
We want to have open, flexible architectures in order to innovate and move fast but our deployment pipelines force us into a pattern (not unlike those 
opinionated frameworks of less recent technologies). 

CloudFormation Templates are JSON. Sounds great at first. Infrastructure as code right? Wrong! IAC works great for tools like Vagrant or Docker
(or Poetry or Maven) because they're intended to be run with destructible build environments. If you want to make complex changes to a running application
though, you'll need to make multiple deployments. If you have a CI/CD pipeline, that means you might have to make multiple commits or have some way of
breaking up merges into their (possibly untested) commits. 

These tools make it hard to create test fixtures and inject dependencies. It's hard to run tests concurrently in one environment. There's no way to describe
complex updates. And, while writing in a more well-known general purpose language isn't a primary argument, it certainly doesn't hurt. 

With AwsChangeManager, you can build your own patterns of architecture and deployment behavior. You can wrap an S3 bucket but you can also wrap an object 
(try that with templates). Not only can you programmatically create resources, you can coordinate data migrations. You can also test all of these things
BEFORE you commit them to version control. 



