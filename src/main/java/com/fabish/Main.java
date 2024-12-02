package com.fabish;

import com.fabish.dao.MemberDao;
import com.fabish.models.Member;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MemberDao memberDAO = new MemberDao();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Member");
            System.out.println("2. List Members");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice == 1) {
                    Member member = new Member();
                    System.out.println("Enter Name:");
                    member.setName(scanner.nextLine());
                    System.out.println("Enter Email:");
                    member.setEmail(scanner.nextLine());
                    System.out.println("Enter Phone:");
                    member.setPhone(scanner.nextLine());
                    System.out.println("Enter Membership Type:");
                    member.setMembershipType(scanner.nextLine());
                    System.out.println("Enter Join Date (YYYY-MM-DD):");
                    member.setJoinDate(scanner.nextLine());
                    System.out.println("Enter Age:");
                    member.setAge(scanner.nextInt());

                    memberDAO.addMember(member);
                    System.out.println("Member added successfully!");

                } else if (choice == 2) {
                    memberDAO.getAllMembers().forEach(member -> {
                        System.out.println(member.getId() + ": " + member.getName() + ", " + member.getEmail());
                    });

                } else if (choice == 3) {
                    Member member = new Member();
                    System.out.println("Enter Member ID to Update:");
                    member.setId(scanner.nextInt());
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter New Name:");
                    member.setName(scanner.nextLine());
                    System.out.println("Enter New Email:");
                    member.setEmail(scanner.nextLine());
                    System.out.println("Enter New Phone:");
                    member.setPhone(scanner.nextLine());
                    System.out.println("Enter New Membership Type:");
                    member.setMembershipType(scanner.nextLine());
                    System.out.println("Enter New Join Date (YYYY-MM-DD):");
                    member.setJoinDate(scanner.nextLine());
                    System.out.println("Enter New Age:");
                    member.setAge(scanner.nextInt());

                    memberDAO.updateMember(member);

                } else if (choice == 4) {
                    System.out.println("Enter Member ID to Delete:");
                    int id = scanner.nextInt();
                    memberDAO.deleteMember(id);

                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("Invalid choice, try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
