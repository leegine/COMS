head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����萔���������Ɖ�X�|���X(WEB3PointCommissionInfoReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����萔���������Ɖ�X�|���X)<BR>
 * �����萔���������Ɖ�X�|���X�N���X<BR>
 * 
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3PointCommissionInfoReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_commissionInfoReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502241605L;
    
    /**
     * (�萔���������ԕ\���t���O)<BR>
     * �萔���������ԕ\���t���O<BR>
     * <BR>
     * true�F �\��<BR>
     * false�F ��\��<BR>
     */
    public boolean freeTermDisplayFlag;
    
    /**
     * (�萔���������ԁi���j)<BR>
     * �萔���������ԁi���j<BR>
     * �iYYYYMMDD�j<BR>
     */
    public Date freeTermFrom;
    
    /**
     * (�萔���������ԁi���j)<BR>
     * �萔���������ԁi���j<BR>
     * �iYYYYMMDD�j<BR>
     */
    public Date freeTermTo;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3PointCommissionInfoReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
