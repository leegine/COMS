head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleCallbackGLRejectTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �R�[���o�b�N�pGL SLE�̋��ۃ^�C�v��`�C���^�t�F�C�X(WEB3SleCallbackGLRejectTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/1 �� (FTL) �V�K�쐬
*/
package webbroker3.slegateway.define;


/**
 * SLE�R�[���o�b�N�����p��GL SLE�̋��ۃ^�C�v��`�C���^�t�F�C�X
 *
 * @@author ���iFTL)
 * @@version 1.0
 */

public class WEB3SleCallbackGLRejectTypeDef {

    /**
     * GL022:�S���������������
     */
    public static final String CHANGE_REJECT_AF_ALL_EXEC = "GL 022";
    
    /**
     * GL007�F�ꕔ���������������
     */
    public static final String CHANGE_REJECT_AF_PART_EXEC = "GL 007";

    /**
     * GL013:������ւ̕����ڑ����m������Ă��܂���
     */
    public static final String EXCHNAGE_PHY_CONN_FAIL = "GL 013";
    
    /**
     * No Chann:�L��GF�|�e�h�w�G���W���i�v���C�}���E�Z�J���_���j���n��Q
     */
    public static final String GF_FIX_FAILOVER_FAIL = "No Chann";
    
    /**
     *  Communi: �L���iOMS�j�ؑցi�v���C�}�����Z�J���_���j����Q
     */
    public static final String GF_OMS_FAILOVER_FAIL = " Communi";
    
    
    /** ���׌N��OMS��d�����G���[�̃G���[�R�[�h */
    public static final String GJS_NW_CODE_DUPLI = "-150906090";

}
@
