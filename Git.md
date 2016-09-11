git status  //查看工作区相比于暂存区有改动的文件<br>
git diff  //查看具体改动文件内容<br>
git add . /git add file某个文件  //将工作区的改动添加到暂存区<br>
git commit –m “[Feature/BugFix] desc”  //添加注释并将暂存区所有改动提交到当前分支<br>
git push origin HEAD:refs/for/develop //推送本地develop分支到远程origin/develop分支<br>
等待jenkins编译success之后，先自行review，没问题之后添加组内成员进行Code ReView。<br>
至此，完成了一次完整的提交流程。<br>
#####3.4自行review时，发现gerrit的代码有问题需要在此基础上修改。<br>
git add . <br>
git commit –amend //对上次提交做出修改<br>
git push origin HEAD:refs/for/develop<br>
#####3.5需要修改部分功能，但此时本工作区已经有修改且不需要提交。<br>
git stash  //将工作区修改的部分隐藏起来<br>
修复紧急bug<br>
重复3.3<br>
git stash pop  //恢复之前隐藏的文件<br>
#####3.6打包测试<br>
git tag -a 3.9.5_2016090901_release -m "tag信息"  //创建tag（尽量少打，出现问题不容易定位，以及更新信息和commitID）<br>
git push origin 3.9.5_2016090901_release  //推送版本信息到远程仓库，会触发jenkins打包并保存在指定目录。<br>
#####3.7想把已经add到暂存区的某个文件撤销回工作区<br>
git reset HEAD fileSrc<br>

