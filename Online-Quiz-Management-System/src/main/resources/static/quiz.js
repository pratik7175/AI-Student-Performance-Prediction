let questions=[], current=0, answers={}, student={};
let seconds=300, timerId=null;

async function init(){
  student=JSON.parse(localStorage.getItem("quizStudent")||"null");
  if(!student){ location.href="/"; return; }
  document.getElementById("welcome").textContent=`Hello, ${student.name}`;
  const res=await fetch("/api/questions");
  questions=await res.json();
  if(!questions.length){document.getElementById("questionArea").innerHTML="<div class='card'>No questions available.</div>";return;}
  renderQuestion();
  timerId=setInterval(tick,1000);
}
function tick(){
  seconds--;
  const m=String(Math.floor(seconds/60)).padStart(2,"0"), s=String(seconds%60).padStart(2,"0");
  document.getElementById("timer").textContent=`${m}:${s}`;
  if(seconds<=0){clearInterval(timerId);submitQuiz();}
}
function renderQuestion(){
  const q=questions[current];
  document.getElementById("progress").textContent=`Question ${current+1} / ${questions.length}`;
  document.getElementById("progressBar").style.width=`${((current+1)/questions.length)*100}%`;
  const selected=answers[q.id]||"";
  const opts=[["A",q.optionA],["B",q.optionB],["C",q.optionC],["D",q.optionD]];
  document.getElementById("questionArea").innerHTML=`
    <div class="question-card">
      <div class="question-number">Question ${current+1}</div>
      <h2>${escapeHtml(q.questionText)}</h2>
      ${opts.map(([v,t])=>`<label class="option"><input type="radio" name="answer" value="${v}" ${selected===v?"checked":""} onchange="saveAnswer('${v}')"><span><b>${v}.</b> ${escapeHtml(t)}</span></label>`).join("")}
    </div>`;
  document.getElementById("prevBtn").classList.toggle("hidden",current===0);
  document.getElementById("nextBtn").classList.toggle("hidden",current===questions.length-1);
  document.getElementById("submitBtn").classList.toggle("hidden",current!==questions.length-1);
}
function saveAnswer(v){answers[questions[current].id]=v}
function previousQuestion(){if(current>0){current--;renderQuestion()}}
function nextQuestion(){if(current<questions.length-1){current++;renderQuestion()}}
async function submitQuiz(){
  clearInterval(timerId);
  let correct=0;
  questions.forEach(q=>{if(answers[q.id]===q.correctAnswer) correct++;});
  const score=Math.round(correct/questions.length*100);
  try{
    const response = await fetch("/api/results",{method:"POST",headers:{"Content-Type":"application/json"},
      body:JSON.stringify({studentName:student.name,email:student.email,totalQuestions:questions.length,correctAnswers:correct,score})});
    if(!response.ok) throw new Error("Could not save your result (HTTP "+response.status+").");
    const saved = await response.json();
    localStorage.setItem("quizResult",JSON.stringify({student,total:questions.length,correct,score,emailSent:saved.emailSent===true}));
    location.href="/result.html";
  }catch(e){
    console.error(e);
    alert("Your result could not be saved. Please check your connection and try submitting again.");
  }
}
function escapeHtml(s){return String(s??"").replace(/[&<>"']/g,m=>({"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#039;"}[m]))}
init();
