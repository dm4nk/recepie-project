package com.dm4nk.recipeproject.commands;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotesCommand {
    Long id;
    String recipeNotes;
}
