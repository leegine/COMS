head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOUploadHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�A�b�v���[�h���𖾍׃��b�Z�[�W�f�[�^(WEB3IPOUploadHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO�A�b�v���[�h���𖾍׃��b�Z�[�W�f�[�^�N���X
 *                                                                    
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPOUploadHistoryUnit extends Message
{
    
    /**
     * ���I�敪<BR>
     * <BR>
     * 1�F�@@�V�K���I<BR>
     * 2�F�@@�J�㒊�I<BR>
     */
    public String lotDiv;
    
    /**
     * �A�b�v���[�h������ԋ敪<BR>
     * <BR>
     * 0�F�@@�A�b�v���[�h�҂�<BR>
     * 1�F�@@�A�b�v���[�h��<BR>
     * 2�F�@@�A�b�v���[�h��
     */
    public String uploadStateDiv;
    
    /**
     * �A�b�v���[�h����
     */
    public String uploadNumber;
    
    /**
     * �A�b�v���[�h�J�n����
     */
    public Date uploadStartDate;
    
    /**
     * �A�b�v���[�h�I������
     */
    public Date uploadEndDate;
    
    /**
     * IPO�G���[�ԍ�<BR>
     * <BR>
     * �� ErrorMessage�e�[�u����PK
     */
    public String ipoErrorId;
    
    /**
     * @@roseuid 4112E4E1023C
     */
    public WEB3IPOUploadHistoryUnit() 
    {
     
    }
}
@
