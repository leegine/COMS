head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBizDateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����C�O�s��J�����_�[�o�^�c�Ɠ�(WEB3MutualBizDateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 �����(���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[ 
*/

package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �����C�O�s��J�����_�[�o�^�c�Ɠ��@@�f�[�^�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3MutualBizDateUnit extends Message 
{
    
    /**
     * �c�Ɠ��̓��t
     */
    public Date bizDate;
    
    /**
     * (�c�Ɠ��敪)<BR>
     * 0:�c�Ɠ��@@1:��c�Ɠ�<BR>
     */
    public String bizDateType;
    
    /**
     * (���M�C�O�s��J�����_�[�o�^�c�Ɠ�)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40C008870307
     */
    public WEB3MutualBizDateUnit() 
    {
     
    }
}
@
