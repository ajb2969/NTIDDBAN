namespace DBAN
{
    partial class DBAN
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.drivesCB = new System.Windows.Forms.ComboBox();
            this.nameLbl = new System.Windows.Forms.Label();
            this.nameTB = new System.Windows.Forms.TextBox();
            this.typeTB = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.sizeTB = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.formatTB = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.dbanBtn = new System.Windows.Forms.Button();
            this.dbanAllBtn = new System.Windows.Forms.Button();
            this.refreshBtn = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(57, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "Drive";
            // 
            // drivesCB
            // 
            this.drivesCB.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.drivesCB.FormattingEnabled = true;
            this.drivesCB.Location = new System.Drawing.Point(18, 42);
            this.drivesCB.Name = "drivesCB";
            this.drivesCB.Size = new System.Drawing.Size(411, 32);
            this.drivesCB.TabIndex = 1;
            this.drivesCB.SelectedIndexChanged += new System.EventHandler(this.drivesCB_SelectedIndexChanged);
            // 
            // nameLbl
            // 
            this.nameLbl.AutoSize = true;
            this.nameLbl.Location = new System.Drawing.Point(18, 94);
            this.nameLbl.Name = "nameLbl";
            this.nameLbl.Size = new System.Drawing.Size(64, 25);
            this.nameLbl.TabIndex = 2;
            this.nameLbl.Text = "Name";
            // 
            // nameTB
            // 
            this.nameTB.Location = new System.Drawing.Point(18, 123);
            this.nameTB.Name = "nameTB";
            this.nameTB.ReadOnly = true;
            this.nameTB.Size = new System.Drawing.Size(411, 29);
            this.nameTB.TabIndex = 3;
            // 
            // typeTB
            // 
            this.typeTB.Location = new System.Drawing.Point(18, 199);
            this.typeTB.Name = "typeTB";
            this.typeTB.ReadOnly = true;
            this.typeTB.Size = new System.Drawing.Size(411, 29);
            this.typeTB.TabIndex = 5;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(18, 170);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(57, 25);
            this.label2.TabIndex = 4;
            this.label2.Text = "Type";
            this.label2.UseWaitCursor = true;
            // 
            // sizeTB
            // 
            this.sizeTB.Location = new System.Drawing.Point(18, 358);
            this.sizeTB.Name = "sizeTB";
            this.sizeTB.ReadOnly = true;
            this.sizeTB.Size = new System.Drawing.Size(411, 29);
            this.sizeTB.TabIndex = 9;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(18, 329);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(51, 25);
            this.label3.TabIndex = 8;
            this.label3.Text = "Size";
            // 
            // formatTB
            // 
            this.formatTB.Location = new System.Drawing.Point(18, 281);
            this.formatTB.Name = "formatTB";
            this.formatTB.ReadOnly = true;
            this.formatTB.Size = new System.Drawing.Size(411, 29);
            this.formatTB.TabIndex = 7;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(18, 252);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(73, 25);
            this.label4.TabIndex = 6;
            this.label4.Text = "Format";
            // 
            // dbanBtn
            // 
            this.dbanBtn.Location = new System.Drawing.Point(166, 411);
            this.dbanBtn.Name = "dbanBtn";
            this.dbanBtn.Size = new System.Drawing.Size(119, 74);
            this.dbanBtn.TabIndex = 10;
            this.dbanBtn.Text = "DBAN";
            this.dbanBtn.UseVisualStyleBackColor = true;
            this.dbanBtn.Click += new System.EventHandler(this.dbanBtn_Click);
            // 
            // dbanAllBtn
            // 
            this.dbanAllBtn.Location = new System.Drawing.Point(310, 411);
            this.dbanAllBtn.Name = "dbanAllBtn";
            this.dbanAllBtn.Size = new System.Drawing.Size(119, 74);
            this.dbanAllBtn.TabIndex = 11;
            this.dbanAllBtn.Text = "DBAN ALL";
            this.dbanAllBtn.UseVisualStyleBackColor = true;
            // 
            // refreshBtn
            // 
            this.refreshBtn.Location = new System.Drawing.Point(18, 411);
            this.refreshBtn.Name = "refreshBtn";
            this.refreshBtn.Size = new System.Drawing.Size(119, 74);
            this.refreshBtn.TabIndex = 12;
            this.refreshBtn.Text = "Refresh";
            this.refreshBtn.UseVisualStyleBackColor = true;
            this.refreshBtn.Click += new System.EventHandler(this.refreshBtn_Click);
            // 
            // DBAN
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(11F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(475, 512);
            this.Controls.Add(this.refreshBtn);
            this.Controls.Add(this.dbanAllBtn);
            this.Controls.Add(this.dbanBtn);
            this.Controls.Add(this.sizeTB);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.formatTB);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.typeTB);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.nameTB);
            this.Controls.Add(this.nameLbl);
            this.Controls.Add(this.drivesCB);
            this.Controls.Add(this.label1);
            this.Name = "DBAN";
            this.Text = "NTID DBAN";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox drivesCB;
        private System.Windows.Forms.Label nameLbl;
        private System.Windows.Forms.TextBox nameTB;
        private System.Windows.Forms.TextBox typeTB;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox sizeTB;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox formatTB;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button dbanBtn;
        private System.Windows.Forms.Button dbanAllBtn;
        private System.Windows.Forms.Button refreshBtn;
    }
}

