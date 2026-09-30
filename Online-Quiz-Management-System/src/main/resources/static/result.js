const r=JSON.parse(localStorage.getItem("quizResult")||"null");
if(!r){location.href="/";}else{
  document.getElementById("resultMessage").textContent=`Well done, ${r.student.name}! Your result has been saved.`;
  document.getElementById("score").textContent=r.score;
  document.getElementById("correct").textContent=r.correct;
  document.getElementById("total").textContent=r.total;
  document.getElementById("wrong").textContent=r.total-r.correct;
  const emailStatus=document.getElementById("emailStatus");
  if(emailStatus){
    emailStatus.textContent=r.emailSent
      ? `A copy of your result was emailed to ${r.student.email}.`
      : `Your result was saved, but the email could not be sent. Please contact the administrator.`;
    emailStatus.classList.toggle("email-success",r.emailSent);
    emailStatus.classList.toggle("email-warning",!r.emailSent);
  }
}
