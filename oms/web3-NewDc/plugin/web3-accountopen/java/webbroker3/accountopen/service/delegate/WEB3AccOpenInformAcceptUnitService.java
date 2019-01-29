head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInformAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �e��A����tUnitService(WEB3AccOpenInformAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/28 �đo�g (���u) �V�K�쐬 ���f�� No.123, No.127, No.135
Revision History : 2007/06/12 �đo�g (���u) ���f�� No.141
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;

/**
 * (�e��A����tUnitService)<BR>
 * �e��A����t�P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3AccOpenInformAcceptUnitService extends Service
{
    /**
     * (notify�e��A����t)<BR>
     * �e��A����t1�����������{����B<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (��t�L���[Params)<BR>
     * �����J�ݓ`�[�o�^��t�L���[Params<BR>
     * @@param l_variousInformParams - (�e��A��Params)<BR>
     * �e��A��Params<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F ������<BR>
     * 9�F �G���[<BR>
     * @@throws WEB3BaseException
     */
    public void notifyInformAccept(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        VariousInformParams l_variousInformParams,
        String l_strProcessDiv) throws WEB3BaseException;
}
@
