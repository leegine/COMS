head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������Ɖ���P�ʃN���X(WEB3RuitoOrderGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �ݓ������Ɖ���P��<BR>
 */
public class WEB3RuitoOrderGroup extends Message
{

    /**
     * ����ID<BR>
     */
    public String id;

    /**
     * �ݐϓ����̃t�@@���h�R�[�h<BR>
     */
    public String ruitoProductCode;

    /**
     * �ݐϓ����̃t�@@���h��<BR>
     */
    public String ruitoProductName;

    /**
     * �����敪(�ݓ�)<BR>
     * <BR>
     * 1:���t  2:�S�����  3:���z�w����@@4�F�����w����<BR>
     */
    public String ruitoDealingType;

    /**
     * ��������<BR>
     */
    public Date orderDate;

    /**
     * �������ʋ敪<BR>
     * <BR>
     * 3: ���z�@@4:����<BR>
     */
    public String ruitoOrderQuantityType;

    /**
     * ��������<BR>
     */
    public String ruitoOrderQuantity;

    /**
     *  1:��t��(�V�K����)�@@        3:������(�V�K����)<BR>
     *  6:�������s(�V�K����)     12:��t��(�������)<BR>
     * 14:������(�������)�@@�@@   15:�������s(�������)<BR>
     * 30�D��t�ρiMRF��񂠂�j  31�D�����ρiMRF��񂠂�j<BR>
     * 32�D�������s�i�l�q�e����������j<BR>
     */
    public String orderState;

    /**
     * ����\�t���O<BR>
     * true�F����\�@@false�F����s��<BR>
     */
    public boolean cancelFlag;

    /**
     * �����`���l��<BR>
     * <BR>
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    public String orderChannel;

    /**
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g<BR>
     * 4�Fi-mode�@@5�FVodafone�@@6�FAU�@@9�FHOST<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    public String orderRootDiv;

    /**
     * �戵�҃R�[�h<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    public String operatorCode;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 4087532101A1
     */
    public WEB3RuitoOrderGroup()
    {

    }
}
@
