head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqLocalCalendarUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���n�J�����_�[���(WEB3AdminFeqLocalCalendarUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���n�J�����_�[���)<BR>
 * ���n�J�����_�[���N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqLocalCalendarUnit extends Message 
{
    
    /**
     * (���t)<BR>
     * ���t
     */
    public Date bizDate;
    
    /**
     * (���t�敪)<BR>
     * ���t�敪<BR>
     * <BR>
     * 0�F��c�Ɠ�<BR>
     * 1�F�c�Ɠ�<BR>
     * <BR>
     * ���V�K�o�^�A���I���̏ꍇ�́Anull<BR>
     * �����N�G�X�g�ŃZ�b�g�����l�́A�ύX��̒l�B
     */
    public String bizDateDiv;
    
    /**
     * (�X�V��)<BR>
     * �X�V��<BR>
     * <BR>
     * ���V�K�o�^�̏ꍇ�́Anull
     */
    public Date updateDate;
    
    /**
     * (���n�J�����_�[���)<BR>
     * �R���X�g���N�^
     * @@roseuid 4200C1A30133
     */
    public WEB3AdminFeqLocalCalendarUnit() 
    {
     
    }
}
@
