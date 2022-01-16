package com.dm4nk.recipeproject.commands;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotesCommand {
    Long id;
    @NotBlank
    String recipeNotes;
}
