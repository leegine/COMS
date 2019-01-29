head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K�����������I����ʃ��X�|���X(WEB3OptionsProductSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���o�� (���u) �V�K�쐬
                 : 2006/08/18 �s�p (���u) ���f��No.536
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�����V�K�����������I����ʃ��X�|���X)<BR>
 * �����w���I�v�V�����V�K�����������I����ʃ��X�|���X�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsProductSelectResponse extends WEB3GenResponse
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141522L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_productSelect";

    /**
     * �V�K���\�z
     */
    public String opTradingPower;

    /**
     * ����s��ꗗ<BR>
     * 1�F�����@@2�F���@@3�F���É�<BR>
     */
    public String[] marketList;

    /**
     * �w����ʈꗗ<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225 <BR>
     */
    public String[] targetProductList;

    /**
     * �����ꗗ<BR>
     * �e�l��YYYYMM�`��<BR>
     */
    public String[] delivaryMonthList;

    /**
     * ����I���x������<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsProductSelectResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsProductSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
