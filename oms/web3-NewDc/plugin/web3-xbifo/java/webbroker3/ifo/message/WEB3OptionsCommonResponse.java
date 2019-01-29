head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�������ʓ��̓��X�|���X(WEB3OptionsCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���� (���u) �V�K�쐬
              001: 2006/07/12 �����F�@@(���u)�@@�d�l�ύX�@@467
Revesion History : 2007/06/08 �����F(���u) ���f�� 639
Revesion History : 2007/06/21 �Ј���(���u) ���f�� 711
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�������ʓ��̓��X�|���X)<BR>
 * �����w���I�v�V�������ʓ��̓��X�|���X�N���X<BR>                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionsCommonResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111708L;
    
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
     * (W�w�l�p���s�����ꗗ)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
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
    public WEB3OptionsCommonResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
