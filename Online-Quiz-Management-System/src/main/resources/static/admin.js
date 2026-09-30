async function loadAll(){
  try {
    const [qres,rres]=await Promise.all([fetch('/api/questions'),fetch('/api/results')]);
    if(!qres.ok||!rres.ok) throw new Error('Could not load admin data');
    const qs=await qres.json(), rs=await rres.json();
    document.getElementById('questions').innerHTML=qs.map(q=>`<tr><td>${q.id}</td><td>${escapeHtml(q.questionText)}</td><td>${escapeHtml(q.category||'')}</td><td><button class="btn danger" onclick="deleteQuestion(${q.id})">Delete</button></td></tr>`).join('') || '<tr><td colspan="4" class="muted">No questions available.</td></tr>';
    document.getElementById('results').innerHTML=rs.length?rs.slice().reverse().map(r=>`<div class="result-row"><b>${escapeHtml(r.studentName)}</b><br>${r.correctAnswers}/${r.totalQuestions} correct • ${r.score}%<br><small>${r.quizDate?new Date(r.quizDate).toLocaleString():''}</small></div>`).join(''):'<p class="muted">No results yet.</p>';
  } catch(e) { console.error(e); document.getElementById('results').innerHTML='<p class="muted">Could not connect to the backend. Check that the app is running.</p>'; }
}
document.getElementById('questionForm').addEventListener('submit',async e=>{
  e.preventDefault();
  const body={questionText:document.getElementById('questionText').value.trim(),optionA:document.getElementById('optionA').value.trim(),optionB:document.getElementById('optionB').value.trim(),optionC:document.getElementById('optionC').value.trim(),optionD:document.getElementById('optionD').value.trim(),correctAnswer:document.getElementById('correctAnswer').value,category:document.getElementById('category').value.trim()||'General'};
  try { const res=await fetch('/api/questions',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(body)}); if(res.ok){e.target.reset();document.getElementById('category').value='General';alert('Question added successfully.');loadAll();}else alert('Could not add question. Check all fields and try again.'); }
  catch(err){alert('Backend connection failed. Make sure the application is running.');}
});
async function deleteQuestion(id){if(!confirm('Delete this question?'))return;try{const res=await fetch(`/api/questions/${id}`,{method:'DELETE'});if(!res.ok)alert('Could not delete question.');await loadAll();}catch(e){alert('Backend connection failed.');}}
function escapeHtml(s){return String(s??'').replace(/[&<>"']/g,m=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#039;'}[m]));}
loadAll();
