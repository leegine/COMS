head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���������e(WEB3AioNewOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���E (���u) �V�K�쐬     
                   2004/10/23 ������ (���u) ���r���[              
Revesion History : 2009/03/12 �đo�g (���u) �d�l�ύX�E���f��No.1109�A1152
*/
package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.ordersubmitter.io.AioNewOrderSpec;

import webbroker3.util.WEB3LogUtility;


/**
 * (���o���������e)<BR>
 * ���o���������e�N���X
 * �iAioNewOrderSpec�̊g���N���X�j
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioNewOrderSpec extends AioNewOrderSpec 
{
    
    /**
     * (���ϋ@@��ID)
     */
    private String paySchemeId;
    
    /**
     * (����ID)
     */
    private Long orderId;

    /**
     * (�E�v�R�[�h)
     */
    private String remarkCode;

    /**
     * (�E�v��)
     */
    private String remarkName;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioNewOrderSpec.class); 
    
    /**
     * (���o���������e)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X�̃��\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j���ϋ@@��ID�̒l���Z�b�g����B<BR>
     * <BR>
     * �R�j����ID�̒l���Z�b�g����B<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g
     * @@param l_orderType - (�������)
     * @@param l_transferType - (�U�փ^�C�v)
     * @@param l_lngProductId - (���iID)
     * @@param l_dblNetAmount - (���z)
     * @@param l_strDescription - (�L�q)
     * @@param l_datEstTransferDate - (�U�֗\���)
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
     * @@param l_lngOrderId - (����ID)
     * @@roseuid 40F5011501AA
     */
    public WEB3AioNewOrderSpec(
        Trader l_trader, 
        OrderTypeEnum l_orderType, 
        AssetTransferTypeEnum l_transferType, 
        long l_lngProductId, 
        double l_dblNetAmount, 
        String l_strDescription, 
        Date l_datEstTransferDate, 
        String l_strPaySchemeId, 
        Long l_lngOrderId) 
    {   
        // �P�j�X�[�p�[�N���X�̃��\�b�h���R�[������B
        super(l_trader,l_orderType,l_transferType, l_lngProductId, l_dblNetAmount,
            l_strDescription, l_datEstTransferDate);
        
        final String STR_METHOD_NAME = "WEB3AioNewOrderSpec(" +
            "Trader l_trader, " +  
            "OrderTypeEnum l_orderType, " +  
            "AssetTransferTypeEnum l_transferType, " +  
            "long l_lngProductId, " + 
            "double l_dblNetAmount, " + 
            "String l_strDescription, " + 
            "Date l_datEstTransferDate, " + 
            "String l_strPaySchemeId, " + 
            "Long l_lngOrderId) ";
        log.entering(STR_METHOD_NAME);
        
        // �Q�j���ϋ@@��ID�̒l���Z�b�g����B
        this.paySchemeId = l_strPaySchemeId;
        
        // �R�j����ID�̒l���Z�b�g����B 
        this.orderId = l_lngOrderId;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (���o���������e)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X�̃��\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j���ϋ@@��ID�̒l���Z�b�g����B<BR>
     * <BR>
     * �R�j����ID�̒l���Z�b�g����B<BR>
     * <BR>
     * �S�j�E�v�R�[�h�̒l���Z�b�g����B<BR>
     * <BR>
     * �T�j�E�v���̒l���Z�b�g����B<BR>
     * <BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@param l_transferType - (�U�փ^�C�v)<BR>
     * �U�փ^�C�v<BR>
     * @@param l_lngProductId - (���iID)<BR>
     * ���iID<BR>
     * @@param l_dblNetAmount - (���z)<BR>
     * ���z<BR>
     * @@param l_strDescription - (�L�q)<BR>
     * �L�q<BR>
     * @@param l_datEstTransferDate - (�U�֗\���)<BR>
     * �U�֗\���<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)<BR>
     * ���ϋ@@��ID<BR>
     * @@param l_orderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strRemarkCode - (�E�v�R�[�h)<BR>
     * �E�v�R�[�h<BR>
     * @@param l_strRemarkName - (�E�v��)<BR>
     * �E�v��<BR>
     */
    public WEB3AioNewOrderSpec(
        Trader l_trader,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_transferType,
        long l_lngProductId,
        double l_dblNetAmount,
        String l_strDescription,
        Date l_datEstTransferDate,
        String l_strPaySchemeId,
        Long l_orderId,
        String l_strRemarkCode,
        String l_strRemarkName)
    {
        //�X�[�p�[�N���X�̃��\�b�h���R�[������
        super(l_trader, l_orderType, l_transferType, l_lngProductId,
            l_dblNetAmount, l_strDescription, l_datEstTransferDate);
        final String STR_METHOD_NAME = "WEB3AioNewOrderSpec(Trader, OrderTypeEnum, "
            + "AssetTransferTypeEnum, long, double, String, Date, String, Long, String, String)";
        log.entering(STR_METHOD_NAME);

        //���ϋ@@��ID�̒l���Z�b�g����B
        this.paySchemeId = l_strPaySchemeId;

        //����ID�̒l���Z�b�g����B
        this.orderId = l_orderId;

        //�E�v�R�[�h�̒l���Z�b�g����B
        this.remarkCode = l_strRemarkCode;

        //�E�v���̒l���Z�b�g����B
        this.remarkName = l_strRemarkName;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���ϋ@@��ID)<BR>
     * ���ϋ@@��ID���擾����B
     * @@return String
     * @@roseuid 40F5028D00EF
     */
    public String getPaySchemeId() 
    {   
        return this.paySchemeId;
    }
    
    /**
     * (get����ID)<BR>
     * ����ID���擾����B
     * @@return long
     * @@roseuid 4100D779007D
     */
    public Long getOrderId() 
    {
        return this.orderId;
    }

    /**
     * (get�E�v�R�[�h)<BR>
     * �E�v�R�[�h���擾����B<BR>
     * @@return String
     */
    public String getRemarkCode()
    {
        return this.remarkCode;
    }

    /**
     * (get�E�v��)<BR>
     * �E�v�����擾����B<BR>
     * @@return String
     */
    public String getRemarkName()
    {
        return this.remarkName;
    }
}
@
