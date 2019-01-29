head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t���̓��X�|���X(WEB3FeqBuyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�O���������t���̓��X�|���X)<BR>
 * �O���������t���̓��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBuyInputResponse extends WEB3FeqInputCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (���t�\�z�i�~�݁j)<BR>
     * ���t�\�z�i�~�݁j<BR>
     */
    public String tradingPowerYen;
    
    /**
     * (���t�\�z�i�O�݁j�ꗗ)<BR>
     * ���t�\�z�i�O�݁j�̔z��<BR>
     * <BR>
     * �����N�G�X�g�Ŗ����R�[�h���w�肳��Ă��ꍇ�́A���̖����̒ʉ݂ɂ��Ă݂̂�ݒ�B<BR>
     */
    public WEB3FeqTradingPowerUnit[] tradingPowerFrnList;
    
    /**
     * (�����敪�ꗗ)<BR>
     * �����敪�ꗗ<BR>
     * <BR>
     * 0�F���<BR>
     * 1�F����<BR>
     */
    public String[] taxTypeList;
    
    /**
     * (���ϋ敪�ꗗ)<BR>
     * ���ϋ敪�ꗗ<BR>
     * <BR>
     * 0�F�~��<BR>
     * 1�F�O��<BR>
     */
    public String[] settleDivList;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * �����N�G�X�g�Ŗ����R�[�h���w�肳��Ă��ꍇ�A�ݒ�B<BR>
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     * <BR>
     * �����N�G�X�g�Ŗ����R�[�h���w�肳��Ă��ꍇ�A�ݒ�B<BR>
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * �����N�G�X�g�Ŗ����R�[�h���w�肳��Ă��ꍇ�A�ݒ�B<BR>
     */
    public String productName;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * ��N1�F���` N2�F�[�Z�� X1�F��C�B<BR>
     */
    public String marketCode;
    
    /**
     * @@roseuid 42CE3A06002E
     */
    public WEB3FeqBuyInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
