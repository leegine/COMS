head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransferSkipOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�փX�L�b�v����(WEB3FXTransferSkipOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/28 �A����(���u) �V�K�쐬
Revesion History : 2008/09/10 ���u��(���u) �d�l�ύX�E���f��976
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (FX�U�փX�L�b�v����)<BR>
 * FX�U�փX�L�b�v����<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3FXTransferSkipOrderUnit extends Message
{
    
    /**
     * (���p�҃R�[�h)<BR>
     * ���p�҃R�[�h<BR>
     */
    public String userCode;

    /**
     * (���o���ԍ�)<BR>
     * ���o���ԍ�<BR>
     */
    public String cashInoutNumber;    

    /**
     * �R���X�g���N�^
     * @@roseuid 43CDDA480186
     */
    public WEB3FXTransferSkipOrderUnit() 
    {
     
    }
}
@
