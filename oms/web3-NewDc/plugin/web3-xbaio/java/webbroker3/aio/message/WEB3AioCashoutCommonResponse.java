head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\�����ʃ��X�|���X(WEB3AioCashoutCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o���\�����ʃ��X�|���X)<BR>
 * (�o���\�����ʃ��X�|���X�N���X)<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutCommonResponse extends WEB3GenResponse 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101125L;    
    
    /**
     * (�U����o�^�敪)<BR>
     * �U����������o�^����Ă��邩�ǂ����̋敪<BR>
     * <BR>
     * 0�F���o�^<BR>
     * 1�F�o�^��<BR>
     */
    public String transRegistDiv;

    /**
     * (��s�R�[�h)<BR>
     * �U����ƂȂ��s�̃R�[�h
     */
    public String bankCode;
    
    /**
     * (��s��)<BR>
     * �U����ƂȂ��s�̖���
     */
    public String bankName;
    
    /**
     * (�x�X��)<BR>
     * �U����ƂȂ��s�̎x�X�̖���
     */
    public String branchBankName;
    
    /**
     * (�a���敪)<BR>
     * �U��������̋敪<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F����<BR>
     * 3�F���̑�<BR>
     * 4�F���~
     */
    public String depositDiv;
    
    /**
     * (�����ԍ�)<BR>
     * �U����ƂȂ�����ԍ�
     */
    public String accountID;
    
    /**
     * @@roseuid 4158EB61018B
     */
    public WEB3AioCashoutCommonResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashoutCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
