Imports MySql.Data.MySqlClient
Imports Mysqlx

Public Class StudentForm

    Dim editMode As Boolean = False
    Private Sub StudentForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Dim sql As String = "SELECT students.id, students.full_name AS 'Student name', students.date_of_birth AS 'Birthday', students.email, students.phone_number AS 'Phone number', students.address, classes.name AS 'Class name' FROM students INNER JOIN classes ON students.class_id = classes.id "
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand(sql, connection)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            dgvStudent.DataSource = table
        End Using
    End Sub

    Private Sub btnAddStudent_Click(sender As Object, e As EventArgs) Handles btnAddStudent.Click
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Dim sql As String = "SELECT id, name FROM classes"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand(sql, connection)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            cbStudentClass.DataSource = table
            cbStudentClass.DisplayMember = "name"
            cbStudentClass.ValueMember = "id"
            txtStudentId.Enabled = True
            txtStudentName.Enabled = True
            txtStudentAddress.Enabled = True
            txtStudentEmail.Enabled = True
            txtStudentPhoneNumber.Enabled = True
            dtpDateOfBirth.Enabled = True
            cbStudentClass.Enabled = True
            btnAddStudent.Enabled = False
            btnSaveStudent.Enabled = True
        End Using
    End Sub

    Private Sub btnSaveStudent_Click(sender As Object, e As EventArgs) Handles btnSaveStudent.Click
        Dim studentId As String = txtStudentId.Text
        Dim studentName As String = txtStudentName.Text
        Dim studentEmail As String = txtStudentEmail.Text
        Dim studentAddress As String = txtStudentAddress.Text
        Dim studentPhoneNumber As String = txtStudentPhoneNumber.Text
        Dim studentDateOfBirth As Date = dtpDateOfBirth.Value
        Dim studentClass As String = cbStudentClass.SelectedValue.ToString()
        If editMode = False Then
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("INSERT INTO students VALUES (@studentId, @studentName, @studentDateOfBirth, @studentEmail, '123456', @studentPhoneNumber, @studentAddress, @studentClass )", connection)
                command.Parameters.AddWithValue("@studentId", studentId)
                command.Parameters.AddWithValue("@studentName", studentName)
                command.Parameters.AddWithValue("@studentDateOfBirth", studentDateOfBirth)
                command.Parameters.AddWithValue("@studentEmail", studentEmail)
                command.Parameters.AddWithValue("@studentPhoneNumber", studentPhoneNumber)
                command.Parameters.AddWithValue("@studentAddress", studentAddress)
                command.Parameters.AddWithValue("@studentClass", studentClass)
                command.ExecuteNonQuery()
            End Using
            StudentForm_Load(sender, e)
            MessageBox.Show("Add Student successfully")
        Else
            Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
            Using connection As New MySqlConnection(connectionString)
                connection.Open()
                Dim command As New MySqlCommand("UPDATE students SET full_name = @studentName, date_of_birth = @studentDateOfBirth, phone_number = @studentPhoneNumber, address = @studentAddress, class_id = @studentClass WHERE id = @studentId ", connection)
                command.Parameters.AddWithValue("@studentId", studentId)
                command.Parameters.AddWithValue("@studentName", studentName)
                command.Parameters.AddWithValue("@studentDateOfBirth", studentDateOfBirth)
                command.Parameters.AddWithValue("@studentPhoneNumber", studentPhoneNumber)
                command.Parameters.AddWithValue("@studentAddress", studentAddress)
                command.Parameters.AddWithValue("@studentClass", studentClass)
                command.ExecuteNonQuery()
            End Using
            StudentForm_Load(sender, e)
            MessageBox.Show("Update Student successfully")
            txtStudentId.ResetText()
            txtStudentName.ResetText()
            txtStudentEmail.ResetText()
            txtStudentAddress.ResetText()
            txtStudentPhoneNumber.ResetText()
            dtpDateOfBirth.ResetText()
            cbStudentClass.ResetText()
            txtStudentId.Enabled = False
            txtStudentName.Enabled = False
            txtStudentAddress.Enabled = False
            txtStudentEmail.Enabled = False
            txtStudentPhoneNumber.Enabled = False
            dtpDateOfBirth.Enabled = False
            cbStudentClass.Enabled = False
            btnAddStudent.Enabled = True
            btnSaveStudent.Enabled = False
        End If
    End Sub

    Private Sub dgvStudent_CellClick(sender As Object, e As DataGridViewCellEventArgs) Handles dgvStudent.CellClick

        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Dim sql As String = "SELECT id, name FROM classes"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand(sql, connection)
            Dim adapter As New MySqlDataAdapter(command)
            Dim table As New DataTable()
            adapter.Fill(table)
            cbStudentClass.DataSource = table
            cbStudentClass.DisplayMember = "name"
            cbStudentClass.ValueMember = "id"
        End Using

        Dim row As DataGridViewRow = dgvStudent.Rows(e.RowIndex)
        Dim studentId As String = row.Cells("id").Value.ToString()
        Dim studentName As String = row.Cells("Student name").Value.ToString()
        Dim studentDateOfBirth As String = row.Cells("Birthday").Value.ToString()
        Dim studentEmail As String = row.Cells("email").Value.ToString()
        Dim studentPhoneNumber As String = row.Cells("Phone number").Value.ToString()
        Dim studentAddress As String = row.Cells("address").Value.ToString()
        Dim studentClass As String = row.Cells("Class name").Value.ToString()
        txtStudentId.Text = studentId
        txtStudentName.Text = studentName
        txtStudentEmail.Text = studentEmail
        txtStudentAddress.Text = studentAddress
        txtStudentPhoneNumber.Text = studentPhoneNumber
        dtpDateOfBirth.Value = studentDateOfBirth

        Dim index As Integer = cbStudentClass.FindStringExact(studentClass)
        If index <> -1 Then
            cbStudentClass.SelectedIndex = index
        End If

        btnAddStudent.Enabled = False
        btnEditStudent.Enabled = True
        btnDeleteStudent.Enabled = True
    End Sub

    Private Sub btnEditStudent_Click(sender As Object, e As EventArgs) Handles btnEditStudent.Click
        editMode = True
        txtStudentId.Enabled = False
        txtStudentName.Enabled = True
        txtStudentAddress.Enabled = True
        txtStudentEmail.Enabled = False
        txtStudentPhoneNumber.Enabled = True
        dtpDateOfBirth.Enabled = True
        cbStudentClass.Enabled = True
        btnEditStudent.Enabled = False
        btnSaveStudent.Enabled = True
        btnDeleteStudent.Enabled = False
    End Sub

    Private Sub btnDeleteStudent_Click(sender As Object, e As EventArgs) Handles btnDeleteStudent.Click
        Dim studentId As String = txtStudentId.Text
        Dim connectionString As String = "Server=localhost;Database=manage_student;Uid=root;Pwd=;"
        Using connection As New MySqlConnection(connectionString)
            connection.Open()
            Dim command As New MySqlCommand("DELETE FROM students WHERE id = @studentId", connection)
            command.Parameters.AddWithValue("@studentId", studentId)
            command.ExecuteNonQuery()
        End Using
        StudentForm_Load(sender, e)
        MessageBox.Show("Delete Student successfully")
        txtStudentId.ResetText()
        txtStudentName.ResetText()
        txtStudentEmail.ResetText()
        txtStudentAddress.ResetText()
        txtStudentPhoneNumber.ResetText()
        dtpDateOfBirth.ResetText()
        cbStudentClass.ResetText()
        txtStudentId.Enabled = False
        txtStudentName.Enabled = False
        txtStudentAddress.Enabled = False
        txtStudentEmail.Enabled = False
        txtStudentPhoneNumber.Enabled = False
        dtpDateOfBirth.Enabled = False
        cbStudentClass.Enabled = False
        btnAddStudent.Enabled = True
        btnSaveStudent.Enabled = False
        btnEditStudent.Enabled = False
        btnDeleteStudent.Enabled = False
    End Sub
End Class