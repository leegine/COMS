head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ServerMgrAccessor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �i�T�[�o�[�T�C�g�Ǘ��j�T�[�o�[Accessor(Web3ServerAccessor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.affinity;

import java.util.*;

import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.kernel.message.*;

/**
 * �i�T�[�o�[�T�C�g�Ǘ��j�T�[�o�[Accessor
 * **/
public final class WEB3ServerMgrAccessor
    implements ServerAccessor
{

    /**
     * webbroker3�J�X�^�}�C�YAffinityAccessor
     */
    private Hashtable web3AffinityAccessors;

    /**
     * �R���X�g���N�^
     * @@param affinityAccessors Hashtable
     */
    public WEB3ServerMgrAccessor(Hashtable affinityAccessors)
    {

        this.web3AffinityAccessors = affinityAccessors;

    }

    public void setConnectTimeOut(int timeout)
    {
        AffinityAccessor[] affinityAccessors = getAffinityAccessors();
        for (int j = 0; j < affinityAccessors.length; j++)
        {
            AffinityAccessor affinityAccessor = affinityAccessors[j];
            ServerAccessor accessors[] = affinityAccessor.getServerAccessors();
            for (int i = 0; i < accessors.length; i++)
            {
                ServerAccessor accessor = accessors[i];
                if (accessor instanceof SocketPoolAccessor)
                {
                    SocketPoolAccessor socketPoolAccessor = (SocketPoolAccessor)
                        accessor;
                    socketPoolAccessor.setTimeOut(timeout);
                }
            }
        }
    }

    /**
     * webbroker3�J�X�^�}�C�YAffinityAccessor���擾����
     *
     * @@return AffinityAccessor[]
     */
    public AffinityAccessor[] getAffinityAccessors()
    {

        AffinityAccessor[] affinityAccessors = new AffinityAccessor[web3AffinityAccessors.
            size()];
        if (web3AffinityAccessors == null)
        {
            return null;
        }
        Iterator iterator = web3AffinityAccessors.values().iterator();
        int i = 0;
        while (iterator.hasNext())
        {
            WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor) iterator.next();
            affinityAccessors[i] = affinityAccessor;
            i++;
        }
        return affinityAccessors;

    }

    /**
     * Request�I�u�W�F�N�g�Ƃ��ă��b�Z�[�W��xTrade�T�[�o�փT�u�~�b�g���A���ʂƂ���Response�I�u�W�F�N�g��Ԃ��܂��B
     *
     * @@param xmlRequest XML���N�G�X�g�d��
     * @@throws CommunicationException �T�[�o�Ƃ̒ʐM�Ɏ��s�����ꍇ
     * @@throws ServerException �ʐM�ɂ͐����������A���N�G�X�g�ɑΉ�����A�v���P�[�V�����ŗL�̃n���h����
     *   ���炩�̗��R�ŏ����������ł��Ȃ������ꍇ
     * @@return ���N�G�X�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g
     */
    public Response doRequestO(String xmlRequest) throws CommunicationException,
        ServerException
    {

        for (int i = 0; i < WEB3ServerTryOrderProviderCallback.TAGNAMES.length; i++)
        {
            String tagName = WEB3ServerTryOrderProviderCallback.TAGNAMES[i];
            if (hasTagName(xmlRequest, tagName))
            {
                WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor)
                    web3AffinityAccessors.get(tagName);
                return affinityAccessor.doRequestO(xmlRequest);
            }
        }
        WEB3AffinityAccessor defaultAffinityAccessor = getDefaultAffinityAccessor();
        return defaultAffinityAccessor.doRequestO(xmlRequest);
    }

    /**
     * Request�I�u�W�F�N�g�Ƃ��ă��b�Z�[�W��xTrade�T�[�o�փT�u�~�b�g���A���ʂƂ���Response�I�u�W�F�N�g��Ԃ��܂��B
     *
     * @@param request ���N�G�X�g�I�u�W�F�N�g�C���X�^���X
     * @@throws CommunicationException �T�[�o�Ƃ̒ʐM�Ɏ��s�����ꍇ
     * @@throws ServerException �ʐM�ɂ͐����������A���N�G�X�g�ɑΉ�����A�v���P�[�V�����ŗL�̃n���h����
     *   ���炩�̗��R�ŏ����������ł��Ȃ������ꍇ
     * @@return ���N�G�X�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g
     */
    public Response doRequest(Request request) throws CommunicationException,
        ServerException
    {
        for (int i = 0; i < WEB3ServerTryOrderProviderCallback.TAGNAMES.length; i++)
        {
            String tagName = WEB3ServerTryOrderProviderCallback.TAGNAMES[i];
            if (hasTagName(request, tagName))
            {
                WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor)
                    web3AffinityAccessors.get(tagName);
                return affinityAccessor.doRequest(request);
            }
        }
        WEB3AffinityAccessor defaultAffinityAccessor = getDefaultAffinityAccessor();
        return defaultAffinityAccessor.doRequest(request);
    }

    /**
     * Request�I�u�W�F�N�g�Ƃ��ă��b�Z�[�W��xTrade�T�[�o�փT�u�~�b�g���A���ʂƂ���Response�I�u�W�F�N�g��Ԃ��܂��B
     *
     * @@param xmlRequest XML���N�G�X�g�d��
     * @@throws CommunicationException �T�[�o�Ƃ̒ʐM�Ɏ��s�����ꍇ
     * @@throws ServerException �ʐM�ɂ͐����������A���N�G�X�g�ɑΉ�����A�v���P�[�V�����ŗL�̃n���h����
     *   ���炩�̗��R�ŏ����������ł��Ȃ������ꍇ
     * @@return XML���X�|���X�d��
     */
    public String doRequest(String xmlRequest) throws CommunicationException,
        ServerException
    {
        for (int i = 0; i < WEB3ServerTryOrderProviderCallback.TAGNAMES.length; i++)
        {
            String tagName = WEB3ServerTryOrderProviderCallback.TAGNAMES[i];
            if (hasTagName(xmlRequest, tagName))
            {
                WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor)
                    web3AffinityAccessors.get(tagName);
                return affinityAccessor.doRequest(xmlRequest);
            }
        }
        WEB3AffinityAccessor defaultAffinityAccessor = getDefaultAffinityAccessor();
        return defaultAffinityAccessor.doRequest(xmlRequest);
    }

    /**
     * Drfault WEB3AffinityAccessor���擾����
     *
     * @@return WEB3AffinityAccessor
     */
    protected WEB3AffinityAccessor getDefaultAffinityAccessor()
    {
        WEB3AffinityAccessor defaultAffinityAccessor = (WEB3AffinityAccessor)
            web3AffinityAccessors.get(WEB3ServerTryOrderProviderCallback.ACCOUNT_ID);
        return defaultAffinityAccessor;
    }

    /**
     * TagName���`�F�b�N����
     *
     * @@param request Request
     * @@param tagname String
     * @@return boolean
     */
    private boolean hasTagName(Request request, String tagname)
    {

        try
        {
            Object tagValue = request.getClass().getField(tagname).get(request);
            if (tagValue != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }

    }

    /**
     * TagName���`�F�b�N����
     *
     * @@param xmlRequest String
     * @@param tagname String
     * @@return boolean
     */
    private boolean hasTagName(String xmlRequest, String tagname)
    {
        String taghead = "<" + tagname + ">";
        String tagtail = "</" + tagname + ">";
        int i = xmlRequest.indexOf(taghead);
        if (i < 0)
        {
            return false;
        }
        i += taghead.length();
        int j = xmlRequest.indexOf(tagtail, i);
        if (j < 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
@
