head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioPrSessionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�q�w�ێ����N���X(WEB3AioPrSessionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ���E (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�o�q�w�ێ����N���X)<BR>
 * �o�q�w�ێ����N���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AioPrSessionUnit extends Message
{
    /**
     * �i�Z�b�V�����L�[�j<BR>
     *  �Z�b�V�����L�[ 
     *  (��ʕ\���Ȃ�)
     */
    public String wolfSession;
    
    /**
     * (�A�v���P�[�V����ID )<BR>
     * �A�v���P�[�V����ID 
     * �i��ʕ\���Ȃ��j
     */
    public String wolfAid;
    
    /**
     * (�Đ����T�[�r�XID)<BR>
     * �Đ����T�[�r�XID
     * �i��ʕ\���Ȃ�)
     */
    public String regetServiceId;
    
    /**
     * (SSID)<BR>
     * SSID
     * �i��ʕ\���Ȃ��j
     */
    public String wolfSsid;
    
    /**
     * (�\���敪)<BR>
     * �\���敪
     */
    public String displayDiv;
    
    /**
     * �i�o�q�w�ێ����N���X�j<BR>
     * �R���X�g���N�^<BR>
     * @@return WEB3AioPrSessionUnit
     * @@roseuid 4158EB620327
     */
    public WEB3AioPrSessionUnit() 
    {

    }
}
@
