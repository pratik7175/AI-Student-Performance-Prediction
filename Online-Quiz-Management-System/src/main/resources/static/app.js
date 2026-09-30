function startQuiz(){
  const name=prompt("Enter your name:");
  if(!name || !name.trim()) return;
  const email=prompt("Enter your email:");
  if(!email || !email.trim()) return;
  localStorage.setItem("quizStudent",JSON.stringify({name:name.trim(),email:email.trim()}));
  location.href="/quiz.html";
}
