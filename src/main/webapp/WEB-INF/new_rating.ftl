<html>
<head>
    <title>Fahrt Bewerten</title>

    <style>
        body {
            margin: 0;
            padding: 64px 32px;
            font-family: Arial;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        body h1 {
            margin-bottom: 32px;
        }

        body .content {
            width: 100%;
            max-width: 768px;
            padding: 32px;
            display: flex;
            flex-direction: column;
            align-items: center;
            background: rgba(0, 0, 0, .0625);
            border-radius: 12px;
            box-shadow: 0 8px 24px -16px rgba(0, 0, 0, .4);
            box-sizing: border-box;
        }

        body .content table {
            width: 100%;
        }

        body .content table tr {
            width: 100%;
            margin: 4px 0;
            display: grid;
            grid-gap: 16px;
            grid-template-columns: 1fr 2fr;
        }

        body .content table tr td:first-child {
            font-weight: bold;
        }

        body .content table tr td textarea {
            width: 100%;
            margin-bottom: 16px;
            min-height: 128px;
            max-height: 256px;
            resize: vertical;
        }

        body .content table tr td label {
            width: 24px;
            margin-right: 24px;
            display: inline-block;
            user-select: none;
            background: white;
            border-radius: 10px;
            text-align: center;
            cursor: pointer;
        }

        body .content button {
            margin-top: 32px;
            margin-left: auto;
            padding: 5px 15px;
            color: white;
            font-size: 1.1em;
            font-weight: bold;
            background: #2C5B9C;
            border: none;
            cursor: pointer;
            box-sizing: border-box;
            transition: all 250ms ease;
        }

        body .content button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <h1>Fahrt Bewertung</h1>

    <div class="content">
        <table>
            <tr>
            <form action="new_rating" method="POST" id="fahrtbewerten">
                    <input type="hidden" name="driveFid" value="${driveFid}">
                <td>Bewertungstext:</td>
                <td>
                    <textarea required placeholder="Hier Bewertungstext eingeben..." name="bewertungsText" maxlength="100" ></textarea>
                </td>
            </tr>
            <tr>
                <td>Bewertungsrating:</td>
                <td>
                    <input type="radio" name="rating" id="1" value="1" checked>
                    <label for="1">1</label>

                    <input type="radio" name="rating" id="2" value="2">
                    <label for="2">2</label>

                    <input type="radio" name="rating" id="3" value="3">
                    <label for="3">3</label>

                    <input type="radio" name="rating" id="4" value="4">
                    <label for="4">4</label>

                    <input type="radio" name="rating" id="5" value="5">
                    <label for="5">5</label>
					 </form>
                </td>
            </tr>
        </table>
			<button type="submit" name="bewertung" form="fahrtbewerten" value="fahrtbewerten">Bewerten</button></td>
    	</div>
   
</body>
</html>