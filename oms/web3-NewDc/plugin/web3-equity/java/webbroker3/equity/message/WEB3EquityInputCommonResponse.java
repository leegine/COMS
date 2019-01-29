head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityInputCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������͋��ʃ��X�|���X(WEB3EquityInputCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�O�U�O
                   2006/07/04 ���� (���u) �d�l�ύX�Ǘ�No.936
                   2006/11/01 �����F(���u) ���f�� 948
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����������͋��ʃ��X�|���X�j<BR>
 * �����������͋��ʉ����@@���X�|���X�f�[�^�N���X
 * 
 * @@author �^��
 * @@version 1.0
 */
public class WEB3EquityInputCommonResponse extends WEB3GenResponse
{

    /**
     * �i�����P���敪�ꗗ�j<BR>
     * 0�F���s�@@�@@�@@1�F�w�l<BR>
     */
    public String[] orderPriceDivList;
    
    /**
     * �i�l�i�����ꗗ�j<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c�����<BR>
     */
    public String[] priceCondList;
    
    /**
     * (���s�����ꗗ)<BR>
     *<BR>
     * 1:�����Ȃ��@@3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String[] execCondList;

    /**
     * (�v�w�l�p���s�����ꗗ)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String[] wlimitExecCondList;

    /**
     * �i�L�������J�n���j<BR>
     * �o����܂Œ����Ŏw��\�ȊJ�n��<BR>
     */
    public Date expirationStartDate;

    /**
     * �i�L�������ŏI���j<BR>
     * �o����܂Œ����Ŏw��\�ȍŏI��<BR>
     */
    public Date expirationEndDate;

    /**
     * �i�L���������j���ꗗ�j<BR>
     * �o����܂Œ����Ŏw��\�͈͓��ɂ���j�Փ�<BR>
     */
    public Date[] holidayList;

    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;

    /**
     * �i�C���T�C�_�[�x���\���t���O�j<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v<BR>
     */
    public boolean insiderWarningFlag;
    
    /**
     * (�����敪)<BR>
     * 1:���ݒl�@@�@@2:���C�z�l�@@�@@3:���C�z�l�@@�@@4:�O���I�l<BR>
     */
    public String currentPriceDiv;
    
    /**
     * �i����(���ݒl)�j<BR>
     * �����i���ݒl�j<BR>
     */
    public String currentPrice;
    
    /**
     * �i�O����j<BR>
     * �O����<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * �i�������(�������\����)�j<BR>
     * �������(�������\���ԁj<BR>
     */
    public Date currentPriceTime;
    
    /**
     * �i���ݒl�j<BR>
     * ���ݒl<BR>
     */
    public String boardCurrentPrice;
    
    /**
     * �i���ݒl�����j<BR>
     * ���ݒl����<BR>
     */
    public Date boardCurrentPriceTime;
    
    /**
     * �i���ݒl�敪�j<BR>
     * ���ݒl�敪 <BR>
     *�i0�F�ʏ� <BR>
     *�@@1�F�I�l�j<BR>
     */
    public String boardCurrentPriceDiv;
    
    /**
     * �i���ݒl�O����j<BR>
     * ���ݒl�O����<BR>
     */
    public String boardChange;
    
    /**
     * �i�o�����j<BR>
     * �o����<BR>
     */
    public String volume;
    
    /**
     * �i�o���������j<BR>
     * �o��������<BR>
     */
    public Date volumeTime;
    
    /**
     * �i���C�z�l�^�C�g���敪�j<BR>
     * ���C�z�l�^�C�g���敪<BR>
     */
    public String askPriceTitle;
    
    /**
     * �i���C�z�l�j<BR>
     * ���C�z�l<BR>
     */
    public String askPrice;
    
    /**
     * �i���C�z�l�����j<BR>
     * ���C�z�l����<BR>
     */
    public Date askPriceTime;
    
    /**
     * �i���C�z�l�^�C�g���敪�j<BR>
     * ���C�z�l�^�C�g���敪<BR>
     */
    public String bidPriceTitle;
    
    /**
     * �i���C�z�l�j<BR>
     * ���C�z�l<BR>
     */
    public String bidPrice;
    
    /**
     * �i���C�z�l�����j<BR>
     * ���C�z�l����<BR>
     */
    public Date bidPriceTime;
    
    /**
     * �i��l�i�j<BR>
     * ��l�i<BR>
     */
    public String basePrice;

    /**
     * SerialVersionUID<BR>
     */
    public static  final long serialVersionUID = 20040520001L;

    /**
     * PTYPE<BR>
     */
    public static  final String PTYPE = "equity_input_common";

    /**
     * @@roseuid 409F41700338
     */
    public WEB3EquityInputCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * @@roseuid 409F41700338
     */
    public WEB3EquityInputCommonResponse()
    {
        
    }    
}
@
