head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkConfirmCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����m�F���ʃ��X�|���X(WEB3MstkConfirmCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 �d��(���u) �V�K�쐬
                   2004/12/09 �K��(SRA) �c�Č��Ή� No.281
                   2005/01/05 ����(SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����~�j�����m�F���ʃ��X�|���X�j�B<BR>
 * <BR>
 * �����~�j�����m�F���ʃ��X�|���X�N���X
 * @@author �d��
 * @@version 1.0
 */
public class WEB3MstkConfirmCommonResponse extends WEB3GenResponse 
{
    
    /**
     * �iPTYPE�j�B
     */
    public final static  String PTYPE = "mstk_confirmCommon";
        
    /**
     * �iSerialVersionUID�j�B
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * �i�T�Z��n����j�B
     */
    public String estimatedPrice;
    
    /**
     * �i�萔�����j�B
     */
    public WEB3MstkCommissionInfoUnit commissionInfo;
    
    /**
     * (�����敪)<BR>
     * 1:���ݒl�@@�@@2:���C�z�l�@@�@@3:���C�z�l�@@�@@4:�O���I�l<BR>
     */
    public String currentPriceDiv;
    
    /**
     * �i�C���T�C�_�[�x���\���t���O�j�B<BR>
     * <BR>
     * true�F�x���\���v<BR>
     * false�F�x���\���s�v
     */
    public boolean insiderWarningFlag;
        
    /**
     * �i����(���ݒl)�j�B
     */
    public String currentPrice;
    
    /**
     * �i�O����j�B
     */
    public String comparedPreviousDay;
    
    /**
     * �i�������(�������\����)�j�B
     */
    public Date currentPriceTime;
    
    /**
     * �i����I���x���j�B<BR>
     * <BR>
     * true�F�x������\������<BR>
     * false�F�x������\�����Ȃ�
     */
    public boolean messageSuspensionFlag;
    
    /**
     * �i�m�F���P���j�B
     */
    public String checkPrice;
    
    /**
     * �i�m�F���������j�B<BR>
     * <BR>
     * ���������s��
     */
    public Date checkDate;
    
    /**
     * �i����ID�j�B
     */
    public String orderId;
    
    /**
     * �i�����~�j�����m�F���ʃ��X�|���X�j�B<BR>
     * <BR>
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3MstkConfirmCommonResponse() 
    {
     
    }
    
    /**
     * �i�����~�j�����m�F���ʃ��X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     * @@param l_request �����~�j�����m�F���ʃ��N�G�X�g
     */
    public WEB3MstkConfirmCommonResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
