Imports MySql.Data.MySqlClient

Public Class SubjectForm
    Dim editMode As Boolean = False
    Private Sub SubjectForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand("SELECT * FROM subjects", connection)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            dgvSubject.DataSource = table
        End Using
    End Sub

    Private Sub btnAddSubject_Click(sender As Object, e As EventArgs) Handles btnAddSubject.Click
        txtSubjectId.Enabled = True
        txtSubjectName.Enabled = True
        txtSubjectDuration.Enabled = True
        txtSubejctSemester.Enabled = True
        txtSubjectMajor.Enabled = True
        btnSaveSubject.Enabled = True
        btnAddSubject.Enabled = False
    End Sub

    Private Sub btnSaveSubject_Click(sender As Object, e As EventArgs) Handles btnSaveSubject.Click
        Dim subjectId As String = txtSubjectId.Text
        Dim subjectName As String = txtSubjectName.Text
        Dim subjectDuration As String = txtSubjectDuration.Text
        Dim subjectSemester As String = txtSubejctSemester.Text
        Dim subjectMajor As String = txtSubjectMajor.Text
        If editMode = False Then
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("INSERT INTO subjects VALUES (@subjectId, @subjectName, @subjectDuration, @subjectSemester, @subjectMajor )", connection)
                command.Parameters.AddWithValue("@subjectId", subjectId)
                command.Parameters.AddWithValue("@subjectName", subjectName)
                command.Parameters.AddWithValue("@subjectDuration", subjectDuration)
                command.Parameters.AddWithValue("@subjectSemester", subjectSemester)
                command.Parameters.AddWithValue("@subjectMajor", subjectMajor)
                command.ExecuteNonQuery()
            End Using
            SubjectForm_Load(sender, e)
            MessageBox.Show("Add subject successfully")
        Else
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("UPDATE subjects SET name = @subjectName, duration = @subjectDuration, semester = @subjectSemester, major = @subjectMajor WHERE id = @subjectId", connection)
                command.Parameters.AddWithValue("@subjectId", subjectId)
                command.Parameters.AddWithValue("@subjectName", subjectName)
                command.Parameters.AddWithValue("@subjectDuration", subjectDuration)
                command.Parameters.AddWithValue("@subjectSemester", subjectSemester)
                command.Parameters.AddWithValue("@subjectMajor", subjectMajor)
                command.ExecuteNonQuery()
            End Using
            SubjectForm_Load(sender, e)
            MessageBox.Show("Update subject successfully")
        End If
        txtSubjectId.Text = ""
        txtSubjectName.Text = ""
        txtSubjectDuration.Text = ""
        txtSubejctSemester.Text = ""
        txtSubjectMajor.Text = ""
        txtSubjectId.Enabled = False
        txtSubjectName.Enabled = False
        txtSubjectDuration.Enabled = False
        txtSubejctSemester.Enabled = False
        txtSubjectMajor.Enabled = False
        btnSaveSubject.Enabled = False
        btnAddSubject.Enabled = True
    End Sub

    Private Sub dgvSubject_CellClick(sender As Object, e As DataGridViewCellEventArgs) Handles dgvSubject.CellClick
        Dim row As DataGridViewRow = dgvSubject.Rows(e.RowIndex)
        Dim subjectId As String = row.Cells("id").Value.ToString()
        Dim subjectName As String = row.Cells("name").Value.ToString()
        Dim subjectDuration As String = row.Cells("duration").Value.ToString()
        Dim subjectSemester As String = row.Cells("semester").Value.ToString()
        Dim subjectMajor As String = row.Cells("major").Value.ToString()
        txtSubjectId.Text = subjectId
        txtSubjectName.Text = subjectName
        txtSubjectDuration.Text = subjectDuration
        txtSubejctSemester.Text = subjectSemester
        txtSubjectMajor.Text = subjectMajor
        btnAddSubject.Enabled = False
        btnEditSubject.Enabled = True
        btnDeleteSubject.Enabled = True
    End Sub

    Private Sub btnEditSubject_Click(sender As Object, e As EventArgs) Handles btnEditSubject.Click
        txtSubjectId.Enabled = False
        txtSubjectName.Enabled = True
        txtSubjectDuration.Enabled = True
        txtSubejctSemester.Enabled = True
        txtSubjectMajor.Enabled = True
        btnSaveSubject.Enabled = True
        btnAddSubject.Enabled = False
        btnEditSubject.Enabled = False
        btnDeleteSubject.Enabled = True
        editMode = True
    End Sub

    Private Sub btnDeleteSubject_Click(sender As Object, e As EventArgs) Handles btnDeleteSubject.Click
        Dim subjectId As String = txtSubjectId.Text
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand("DELETE FROM subjects WHERE id = @subjectId", connection)
            command.Parameters.AddWithValue("@subjectId", subjectId)
            command.ExecuteNonQuery()
        End Using
        SubjectForm_Load(sender, e)
        MessageBox.Show("Delete Subject successfully")
        btnDeleteSubject.Enabled = False
        btnEditSubject.Enabled = False
        btnAddSubject.Enabled = True
        txtSubjectId.Text = ""
        txtSubjectName.Text = ""
        txtSubjectDuration.Text = ""
        txtSubejctSemester.Text = ""
        txtSubjectMajor.Text = ""
        txtSubjectName.Enabled = False
        txtSubjectDuration.Enabled = False
        txtSubejctSemester.Enabled = False
        txtSubjectMajor.Enabled = False
        btnSaveSubject.Enabled = False
    End Sub
End Class