head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����s���m��FLAG�@@�萔��`�C���^�[�t�F�[�X(WEB3IfoDepositFixedIfoDepositFlgDiv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/03 nakazato(ACT) �V�K�쐬
*/

package webbroker3.ifodeposit.define;

/**
 * �؋����s���m��FLAG�@@�萔��`�C���^�[�t�F�[�X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoDepositCalcResultSaveDiv
{
    
    /**
     * 0�F������ <BR>
     */
    public static final int STATUS_NONE = 0;
    
    /**
     * 1�F�������� <BR>
     */
    public static final int STATUS_FINISH = 1;
    
    /**
     * 9�F�����G���[<BR>
     */
    public static final int STATUS_ERROR = 9;
    
    /**
     * 0018�FNK225<BR>
     */
    public static final String NK225 = "0018";
    
    /**
     * 0019�F�~�jNK225<BR>
     */
    public static final String MINI_NK225 = "0019";
    
    /**
     * ���b�N�����敪�@@0�F�ҋ@@<BR>
     */
    public static final String LOCK_NONE = "0";
    
    /**
     * ���b�N�����敪�@@1�F������<BR>
     */
    public static final String LOCK_PROC = "1";
    
    public static final int ERROR_MESSAGE_LENGTH = 100;
    
}
@
