head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨���ʓ��̓��X�|���X(WEB3FuturesCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ���Q (���u) �V�K�쐬
                   2006/07/28 �����F(���u)�@@���f��467
Revesion History : 2007/06/11 �Ј���(���u) �d�l�ύX���f��No.639
Revesion History : 2007/06/21 ���^�](���u) �d�l�ύX���f��No.711
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���敨���ʓ��̓��X�|���X)<BR>
 * �����w���敨���ʓ��̓��X�|���X�N���X<BR>
 *                                                                     
 * @@author ���Q
 * @@version 1.0
 */
public class WEB3FuturesCommonResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407201154L;
    
    /**
     * (�����P���敪�ꗗ)<BR>
     * 0�F���s�@@�@@1�F�w�l<BR>
     */
    public String[] orderPriceDivList;
    
    /**
     * (���s�����ꗗ)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String[] execCondList;
    
    /**
     * (���������敪�ꗗ)<BR>
     * 1�F��������@@2�F�o����܂Œ����@@3�F�[��܂Œ���<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * (�L�������J�n��)<BR>
     * �o����܂Œ����Ɏw��ł���ŏ��̓�<BR>
     * <BR>
     * ���������敪�Ɂu�o����܂Œ����v������ꍇ�ݒ�<BR>
     */
    public Date expirationStartDate;
    
    /**
     * (�L�������ŏI��)<BR>
     * �o����܂Œ����Ɏw��ł���Ō�̓�<BR>
     * <BR>
     * ���������敪�Ɂu�o����܂Œ����v������ꍇ�ݒ�<BR>
     */
    public Date expirationEndDate;
    
    /**
     * (�L���������j���ꗗ)<BR>
     * �o����܂Œ����Ɏw��ł�����Ԓ��̏j���ꗗ<BR>
     * <BR>
     * ���������敪�Ɂu�o����܂Œ����v������ꍇ�ł��A���Ԓ��ɏj���̂���ꍇ�ɐݒ�<BR>
     */
    public Date[] holidayList;
    
    /**
     * (���������敪�ꗗ)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String[] orderCondTypeList;
    
    /**
     * (�v�w�l�p���s�����ꗗ)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s <BR>
     */
    public String[] wlimitExecCondList;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3FuturesCommonResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
