head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqInputCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������͋��ʃ��X�|���X(WEB3FeqInputCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�O���������͋��ʃ��X�|���X)<BR>
 * �O���������͋��ʃ��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqInputCommonResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_inputCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�����P���敪�ꗗ)<BR>
     * �����P���敪�ꗗ <BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l <BR>
     */
    public String[] orderPriceDivList;
    
    
    /**
     * (���s�����ꗗ)<BR>
     * ���s�����ꗗ<BR>
     * <BR>
     * 1�F�����Ȃ�<BR>
     * 3�F��t<BR>
     * 4�F����<BR>
     * 7�F�s�o���������s<BR>
     */
    public String[] execCondList;
    
    /**
     * (���������敪�ꗗ)<BR>
     * ���������敪�ꗗ<BR>
     * <BR>
     * 1�F��������<BR>
     * 2�F�o����܂Œ���<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * (�L�������J�n��)<BR>
     * �L�������J�n��<BR>
     */
    public Date expirationStartDate;
    
    /**
     * (�L�������I����)<BR>
     * �L�������I����<BR>
     */
    public Date expirationEndDate;
    
    /**
     * (�L���������j���ꗗ)<BR>
     * �L���������j���ꗗ<BR>
     */
    public Date[] holidayList;
    
    /**
     * (���������ꗗ)<BR>
     * ���������ꗗ<BR>
     * <BR>
     * 0�F�w��Ȃ�<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     */
    public String[] orderCondTypeList;
    
    /**
     * (�����擾�敪)<BR>
     * �����擾�敪<BR>
     * <BR>
     * 1�F���ݒl<BR>
     * 2�F���C�z�l<BR>
     * 3�F���C�z�l<BR>
     * 4�F�O���I�l<BR>
     * <BR>
     * ���l�����Ă��Ȃ��Ƃ��͖��ݒ�<BR>
     */
    public String currentPriceGetDiv;
    
    /**
     * (����)<BR>
     * ����<BR>
     * <BR>
     * ���l�����Ă��Ȃ��Ƃ��͖��ݒ�<BR>
     */
    public String currentPrice;
    
    /**
     * (�O����)<BR>
     * �O����<BR>
     * <BR>
     * ���l�����Ă��Ȃ��Ƃ��͖��ݒ�<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (�������)<BR>
     * �������<BR>
     * <BR>
     * ���l�����Ă��Ȃ��Ƃ��͖��ݒ�<BR>
     */
    public Date currentPriceTime;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 42CE3A0900CB
     */
    public WEB3FeqInputCommonResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqInputCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
