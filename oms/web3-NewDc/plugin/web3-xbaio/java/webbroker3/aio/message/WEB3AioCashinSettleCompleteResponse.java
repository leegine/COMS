head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϊ������X�|���X(WEB3AioCashinSettleCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬    
                   2004/10/22 ���� (���u) ���r���[                
   
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (���ϊ������X�|���X)<BR>
 * ���ϊ������X�|���X�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCompleteResponse extends WEB3AioCashinCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_settle_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (.com�f�r�b�g���ώ���ԍ�)<BR>
     * �I�����C�����ςŎg�p���ꂽ���ώ���ԍ�<BR>
     */
    public String comDebitNumber;
    
    /**
     * (�������z)<BR>
     * �I�����C�����ς̓������z<BR>
     */
    public String cashinAmt;
    
    /**
     * (�U���\���)<BR>
     * �I�����C�����ς̐U���\���<BR>
     */
    public Date transScheduledDate;
    
    /**
     * (�،������U�֓�)<BR>
     * �ڋq�̏،������ւ̐U�֓�
     */
    public Date accountTransDate;
    
    /**
     * (�X�V����)<BR>
     * DB�̍X�V����<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (����ID)<BR>
     * DB�o�^���ꂽ����ID<BR>
     */
    public String orderId;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4158EB3303D4
     */
    public WEB3AioCashinSettleCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashinSettleCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
