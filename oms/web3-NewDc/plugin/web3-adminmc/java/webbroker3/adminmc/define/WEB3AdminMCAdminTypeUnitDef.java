head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminTypeUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃^�C�v��� �萔��`�C���^�t�F�C�X(WEB3AdminMCAdminTypeUnitDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 �Ɍ��t (���u) �V�K�쐬
*/
package webbroker3.adminmc.define;

/**
 * �Ǘ��҃^�C�v��� �萔��`�C���^�t�F�C�X
 * 
 * @@author �Ɍ��t(���u)
 * @@version 1.0
 */
public interface WEB3AdminMCAdminTypeUnitDef
{
    /**
     * permissionLevel: �������x���R�[�h
     */
    public static final String PERMISSION_LEVEL = "permissionLevel";
    
    /**
     * permissionLevelName: �������x������
     */
    public static final String PERMISSION_LEVEL_NAME = "permissionLevelName";
    
    /**
     * dirAdminFlag: DIR�Ǘ��҃t���O
     */
    public static final String DIR_ADMIN_FLAG = "dirAdminFlag";

    /**
     * allBranchPermissionFlag: �S���X���t���O
     */
    public static final String ALL_BRANCH_PERMISSION_FLAG = "allBranchPermissionFlag";
    
    /**
     * branchCode: DIR�Ǘ��҂̊J�n���� 
     */
    public static final char PERMISSION_LEVEL_NAME_FIRST = '9';
}
@
