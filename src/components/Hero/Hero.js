import Link from 'next/link';
import React from 'react';

import { Section, SectionText, SectionTitle, SectionSubText } from '../../styles/GlobalComponents';
import Button from '../../styles/GlobalComponents/Button';
import { LeftSection } from './HeroStyles';

const Hero = () => (
  <Section row nopadding>
    <LeftSection>

      <SectionTitle main center>
        Hi,<br/>
        I'm {"<"}Joseph Evans{"/>"}
      </SectionTitle>
      <SectionText>
        {/* Full-Stack Software Engineer */}
        {/* from UC Santa Cruz */}
        <SectionSubText>
          I'm a Software Engineer from the University of California,
          Santa Cruz and
          the creator of the Spearmint Programming Language. 
        </SectionSubText>
      </SectionText>

      <Button onClick={() => window.location = 'mailTo: Josephev4@gmail.com'}>Get in touch!</Button>

    </LeftSection>
  </Section>
);

export default Hero;