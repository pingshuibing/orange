ORANGE PROJECT
==============
This is the real repository. orangecalculator will be used as a testing repo


SETUP INSTRUCTION
=================
0. Install IDE
* Eclipse Juno for Java EE Developers
  http://www.eclipse.org/downloads/

* Google App Engine plugin
  In Eclipse Help / Install New Software:
  Add: http://dl.google.com/eclipse/plugin/4.2
  Install: "Google Plugin for Eclipse" and "SDKs"

* A GIT client

1. Clone this repository
  $ git clone <URL> /path/to/project

2. Open Eclipse, choose /path/to/project as Workspace path

3. Goto File > Import..
  Select General > Existing Projects into Workspace
  Select /path/to/project/SolarPowerCalc as project path
  Finish


RECOMMENDED WORKFLOW
====================
1. Develop on a local branch rather than "master" branch
* Assume we are in "master" branch, make sure repo is up to date before
  developing:

  Fetch source code (should be careful)
  $ git fetch
  OR Fetch source code and merge with your local chances (not recommended)
  $ git pull
  OR Fetch source code and rebase your local changes (recommended)
  $ git pull --rebase

+ If the branch does not exist:
  Create and checkout a new branch
  $ git checkout -b <BRANCH>

+ If the branch exists:
  Check out the branch
  $ git checkout <BRANCH>
  Bring changes from master to this branch if needed
  $ git rebase master

* Edit and commit your changes
  $ git add ...
  $ git commit ...

* If a public branch is needed in order to work collaboratively or without
  interfere with the master branch:

  Save this branch to the public repo
  $ git push origin <BRANCH>

  For next development cycle, see "If the branch exists" above, just stay
  in that branch, commit and push

* Bring changes into master branch:

  Go back to master branch
  $ git checkout master
  Merge your local changes
  $ git merge <BRANCH>
  OR rebase if the master is modified during development
  $ git rebase <BRANCH>
  Push changes to the public repo
  $ git push

See http://mettadore.com/analysis/a-simple-git-rebase-workflow-explained/


CREATE A TEST CASE
==================
1. Right-click on the class under test,
  Select New > JUnit Test Case

2. In JUnit Test Case dialog,
  Select JUnit 4
  Change source folder to: SolarPowerCalc/test


