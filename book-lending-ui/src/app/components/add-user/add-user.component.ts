import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-add-user',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './add-user.component.html'
})
export class AddUserComponent {
  userForm: FormGroup;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      this.userService.createUser(this.userForm.value).subscribe(() => {
        alert('User created successfully');
        this.userForm.reset();
      });
    }
  }
}
