head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�ɗ��𖾍�(WEB3AioHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 䈋� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (���o�ɗ��𖾍�)<BR>
 * ���o�ɗ��𖾍׃N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3AioHistoryUnit extends Message
{
    
    /**
     * (��n��)<BR>
     * ��n��
     */
    public Date deliveryDate;
    
    /**
     * (���i�O���[�v)<BR>
     * ���i�O���[�v<BR>
     * <BR>
     * A�F ����<BR>
     * B�F �O������<BR>
     * C�F ���M<BR>
     * D�F ��
     */
    public String productGroup;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������
     */
    public String productName;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F ���<BR>
     * 1�F ����
     */
    public String taxType;
    
    /**
     * (���o�ɃO���[�v)<BR>
     * ���o�ɃO���[�v<BR>
     * <BR>
     * A�F �o��<BR>
     * B�F ����<BR>
     * C�F �o��(��������)<BR>
     * D�F ����(��������)<BR>
     * E�F �o��(�L������)<BR>
     * F�F ����(�L������)<BR>
     * G�F �o��(�z�ʈ���)<BR>
     * H�F ����(�z�ʈ���)<BR>
     * I�F �o��(��������)<BR>
     * J�F ����(��������)<BR>
     * K�F �o��(��Ѝ���)<BR>
     * L�F ����(��Ѝ���)<BR>
     * M�F �o��(��ē]��)<BR>
     * N�F ����(��ē]��)
     */
    public String inputOutputDetailGroup;
    
    /**
     * (����)<BR>
     * ����
     */
    public String quantity;
    
    /**
     * (���ʒP��)<BR>
     * ���ʒP��<BR>
     * <BR>
     * A�F ��<BR>
     * B�F ��
     */
    public String quantityUnit;
    
    /**
     * (�P��)<BR>
     * �P��
     */
    public String price;
    
    /**
     * (���o�ɗ��𖾍�)<BR>
     * �R���X�g���N�^
     * @@roseuid 41B6DB89013F
     */
    public WEB3AioHistoryUnit() 
    {
     
    }
}
@
