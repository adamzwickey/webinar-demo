function App() {
    const [messages, setMessages] = React.useState([]);
    const [newMessage, setNewMessage] = React.useState("");

    const fetchMessages = async () => {
        const resp = await fetch('/messages');
        if (resp.ok) {
            const data = await resp.json();
            setMessages(data);
        }
    };

    React.useEffect(() => {
        fetchMessages();
    }, []);

    const saveMessage = async () => {
        if (!newMessage.trim()) return;
        const resp = await fetch('/messages', {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ message: newMessage })
        });
        if (resp.ok) {
            const saved = await resp.json();
            setMessages([...messages, saved]);
            setNewMessage("");
        }
    };

    return (
        <div style={{ padding: '1em', fontFamily: 'Arial' }}>
            <h1>Demo Messages</h1>
            <ul>
                {messages.map(m => (
                    <li key={m.guid}>{m.message}</li>
                ))}
            </ul>
            <input
                type="text"
                value={newMessage}
                onChange={e => setNewMessage(e.target.value)}
                placeholder="Enter a message"
            />
            <button onClick={saveMessage}>Save</button>
        </div>
    );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
