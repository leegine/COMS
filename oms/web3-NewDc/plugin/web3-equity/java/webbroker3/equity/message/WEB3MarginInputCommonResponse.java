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
filename	WEB3MarginInputCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ʓ��̓��X�|���X(WEB3MarginInputCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �c�Č��Ή�
                   2006/11/02 �����F(���u) ���f�� 948
                   2006/12/14 ������@@(���u)�@@���f��No.1082
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * �i�M�p������ʓ��̓��X�|���X�j�B<br>
 * <br>
 * �M�p������ʓ��̓��X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginInputCommonResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_inputCommon";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�����P���敪�ꗗ)<BR>
     * 0�F���s�@@�@@�@@1�F�w�l
     */    
    public String[] orderPriceDivList;
    
    /**
     * (�l�i�����ꗗ)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c�����
     */
    public String[] priceCondList;
    
    
    /**
     * (���s�����ꗗ)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s
     */
    public String[] execCondList;

    /**
     * (�v�w�l�p���s�����ꗗ)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String[] wlimitExecCondList;

    /**
     * (�L�������J�n��)<BR>
     * �o����܂Œ����Ɏw��ł���ŏ��̓�
     */
    public Date expirationStartDate;
    
    /**
     * (�L�������ŏI��)<BR>
     * �o����܂Œ����Ɏw��ł���Ō�̓�
     */
    public Date expirationEndDate;
    
    /**
     * (�L���������j���ꗗ)<BR>
     * �o����܂Œ����Ɏw��ł�����Ԓ��̏j���ꗗ
     */
    public Date[] holidayList;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ
     */
    public String[] messageSuspension;
    
    /**
     * (�C���T�C�_�[�x���\���t���O)<BR>
     * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v
     */
    public boolean insiderWarningFlag;
    
    /**
     * (�����敪)<BR>
     * �����敪 <BR>
     * �i1�F���ݒl <BR>
     * �@@2�F���C�z�l <BR>
     * �@@3�F���C�z�l <BR>
     * �@@4�F�O���I�l�j<BR>
     */
    public String currentPriceDiv;
    
    /**
     * (�����i���ݒl�j<BR>
     * �����i���ݒl�j
     */
    public String currentPrice;
    
    /**
     * (�O����)<BR>
     * �O����
     */
    public String comparedPreviousDay;
    
    /**
     * (�������(�������\���ԁj)<BR>
     * �������(�������\���ԁj
     */
    public Date currentPriceTime;
    
    
    /**
     * (���ݒl)<BR>
     */
    public String boardCurrentPrice;

    /**
     * (���ݒl����)<BR>
     */
    public Date boardCurrentPriceTime;

    /**
     * (���ݒl�敪)<BR>
     */
    public String boardCurrentPriceDiv;

    /**
     * (���ݒl�O����)<BR>
     */
    public String boardChange;

    /**
     * (�o����)<BR>
     */
    public String volume;

    /**
     * (�o��������)<BR>
     */
    public Date volumeTime;

    /**
     * (���C�z�l�^�C�g���敪)<BR>
     */
    public String askPriceTitle;

    /**
     * (���C�z�l)<BR>
     */
    public String askPrice;

    /**
     * (���C�z�l����)<BR>
     */
    public Date askPriceTime;

    /**
     * (���C�z�l�^�C�g���敪)<BR>
     */
    public String bidPriceTitle;

    /**
     * (���C�z�l)<BR>
     */
    public String bidPrice;

    /**
     * (���C�z�l����)<BR>
     */
    public Date bidPriceTime;

    /**
     * (��l�i)<BR>
     */
    public String basePrice;

    /**
     * @@roseuid 41403F700264
     */
    public WEB3MarginInputCommonResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginInputCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
